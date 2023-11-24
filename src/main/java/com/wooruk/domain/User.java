package com.wooruk.domain;

public class User {

    private String id;
    private String userId;
    private String password;
    private String email;
    private String nick;

    public User(String id, String userId, String password, String email, String nick) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", userId='" + userId + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", nick='" + nick + '\'' +
            '}';
    }

    public String getId() {
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

    public String getNick() {
        return nick;
    }
}
