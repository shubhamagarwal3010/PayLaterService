package com.paylater.user_service;

import com.paylater.user_service.exception.InternalException;
import com.paylater.user_service.exception.UserAlreadyExists;
import com.paylater.user_service.exception.UserDataNotValid;
import com.paylater.user_service.exception.UserNotFound;

public interface IPlatformUserService {

    void createUser(String userName, String email) throws UserAlreadyExists, UserDataNotValid;

    String getUser(String userName) throws UserNotFound, InternalException;

    String getAllUsers() throws InternalException;
}
