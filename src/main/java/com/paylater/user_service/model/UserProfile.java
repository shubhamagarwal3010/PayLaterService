package com.paylater.user_service.model;

import com.paylater.user_service.exception.UserDataNotValid;
import com.paylater.utils.Validation;

import java.util.Date;
import java.util.Objects;

public class UserProfile {
    private final Date createdAt;
    private String userName;
    private String email;

    public UserProfile(String userName, String email) throws UserDataNotValid {
        this.setUserName(userName);
        this.setEmail(email);
        this.createdAt = new Date();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) throws UserDataNotValid {
        if (!Validation.isNameValid(userName)) {
            throw new UserDataNotValid("Username is not valid");
        }
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws UserDataNotValid {
        if (!Validation.isNameValid(email)) {
            throw new UserDataNotValid("Email id is not valid");
        }
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return userName.equals(that.userName) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email);
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
