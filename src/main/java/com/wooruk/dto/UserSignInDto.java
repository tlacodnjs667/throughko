package com.wooruk.dto;

import com.wooruk.domain.User;

public class UserSignInDto {

    private String userId;
    private String password;

    public UserSignInDto() {
    }

    public UserSignInDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "UserSignUpDto{" +
            "userId='" + userId + '\'' +
            ", password='" + password +
            '}';
    }

    public User toUser() {
        return new User(null, userId, password, null, null);
    }

}
