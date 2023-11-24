package com.wooruk.dto;

import com.wooruk.domain.User;

public class UserSignUpDto {

    private String userId;
    private String password;
    private String email;
    private String nickname;

    public UserSignUpDto() {
    }

    public UserSignUpDto(String userId, String password, String email, String nickname) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "UserSignUpDto{" +
            "userId='" + userId + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", nickname='" + nickname + '\'' +
            '}';
    }

    public User toUser() {
        return new User(null, userId, password, email, nickname);
    }

}
