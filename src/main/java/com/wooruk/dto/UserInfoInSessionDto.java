package com.wooruk.dto;

import com.wooruk.domain.User;

public class UserInfoInSessionDto {

    private int userPk;
    private String nickname;

    public UserInfoInSessionDto(int userPk, String nickname) {
        this.userPk = userPk;
        this.nickname = nickname;
    }

    public static UserInfoInSessionDto valueOf(User user) {
        if (user == null) {
            return null;
        }
        return new UserInfoInSessionDto(user.getUser_pk(), user.getNickname());
    }

    @Override
    public String toString() {
        return "UserInfoInSessionDto{" +
            "user_pk=" + userPk +
            ", nickname='" + nickname + '\'' +
            '}';
    }

    public int getUserPk() {
        return userPk;
    }

    public String getNickname() {
        return nickname;
    }
}
