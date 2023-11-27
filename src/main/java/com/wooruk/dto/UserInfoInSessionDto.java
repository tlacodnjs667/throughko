package com.wooruk.dto;

import com.wooruk.domain.User;

public class UserInfoInSessionDto {

    private int userPk;
    private String nickname;
    private String userId;

    public UserInfoInSessionDto(int userPk, String nickname, String userId) {
        this.userPk = userPk;
        this.nickname = nickname;
        this.userId = userId;
    }

    public static UserInfoInSessionDto valueOf(User user) {
        if (user == null) {
            return null;
        }
        return new UserInfoInSessionDto(user.getUserPk(), user.getNickname(), user.getUserId());
    }

    @Override
    public String toString() {
        return "UserInfoInSessionDto{" +
               "userPk=" + userPk +
               ", nickname='" + nickname + '\'' +
               ", userId='" + userId + '\'' +
               '}';
    }

    public String getUserId() {
        return userId;
    }


    public int getUserPk() {
        return userPk;
    }

    public String getNickname() {
        return nickname;
    }
}
