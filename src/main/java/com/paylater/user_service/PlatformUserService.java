package com.paylater.user_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paylater.user_service.exception.InternalException;
import com.paylater.user_service.exception.UserAlreadyExists;
import com.paylater.user_service.exception.UserDataNotValid;
import com.paylater.user_service.exception.UserNotFound;
import com.paylater.user_service.model.UserProfile;
import com.paylater.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlatformUserService implements IPlatformUserService {

    private final Map<String, UserProfile> userProfileMap = new HashMap<>();

    @Override
    public void createUser(String userName, String email) throws UserAlreadyExists, UserDataNotValid {
        if (!userProfileMap.containsKey(userName)) {
            userProfileMap.put(userName, new UserProfile(userName, email));
        } else {
            throw new UserAlreadyExists();
        }
    }

    @Override
    public String getUser(String userName) throws UserNotFound, InternalException {
        if (userProfileMap.containsKey(userName)) {
            try {
                return JsonUtil.objectToJson(userProfileMap.get(userName));
            } catch (JsonProcessingException e) {
                throw new InternalException("Internal Server error during data parsing");
            }
        }
        throw new UserNotFound();
    }

    @Override
    public String getAllUsers() throws InternalException {
        try {
            return JsonUtil.objectToJson(new ArrayList<>(userProfileMap.values()));
        } catch (JsonProcessingException e) {
            throw new InternalException("Internal Server error during data parsing");
        }
    }
}
