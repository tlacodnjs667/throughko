package com.wooruk.dto;

public class UserForPostDto {

    Integer id;
    String nickname;

    public UserForPostDto(String nickname) {
        this.nickname = nickname;
    }

    public UserForPostDto(Integer id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

}
