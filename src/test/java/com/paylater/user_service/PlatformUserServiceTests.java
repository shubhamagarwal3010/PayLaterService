package com.paylater.user_service;

import com.paylater.AppConfig;
import com.paylater.user_service.exception.InternalException;
import com.paylater.user_service.exception.UserAlreadyExists;
import com.paylater.user_service.exception.UserDataNotValid;
import com.paylater.user_service.exception.UserNotFound;
import com.paylater.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PlatformUserServiceTests {

    @Autowired
    private IPlatformUserService userService;

    @Test
    void testAddUser() {
        try {
            userService.createUser("userName123", "email@email.com");
        } catch (UserAlreadyExists | UserDataNotValid e) {
            assertNull(e);
        }
        try {
            String user = userService.getUser("userName123");
            assertEquals("userName123", JsonUtil.getStringValue(user, "userName"));
            assertEquals("email@email.com", JsonUtil.getStringValue(user, "email"));
        } catch (UserNotFound | InternalException e) {
            assertNull(e);
        }
    }

    @Test
    void testShouldThrowExceptionIfSameUserIsAddedTwice() {
        try {
            userService.createUser("userName123", "email@email.com");
            userService.createUser("userName123", "email@email.com");
        } catch (UserAlreadyExists | UserDataNotValid e) {
            assertNotNull(e);
        }
    }

    @Test
    void testShouldThrowExceptionIfProductIsNotFound() {
        try {
            userService.getUser("userName123");
        } catch (UserNotFound | InternalException e) {
            assertNotNull(e);
        }
    }
}
