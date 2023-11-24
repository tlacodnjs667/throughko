package com.wooruk.domain;

public class User {

    private Integer id;
    private String userId;
    private String password;
    private String email;
    private String nickname;

    public User(Integer id, String userId, String password, String email, String nickname ) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname ;
    }

    public User(Integer id, String nickname ) {
        this.id = id;
        this.nickname = nickname ;
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", userId='" + userId + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", nick='" + nickname + '\'' +
            '}';
    }

    public int getId() {
        return id;
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

    public String getNickname () {
        return nickname;
    }
}
