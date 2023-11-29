package com.wooruk.domain;

import com.wooruk.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
    private final Logger log = LoggerFactory.getLogger(UserDao.class);
    private Integer user_pk;
    private String user_id;
    private String password;
    private String email;
    private String nickname;

    public User(Integer user_pk, String user_id, String email, String nickname) {
        this.user_pk = user_pk;
        this.user_id = user_id;
        this.email = email;
        this.nickname = nickname;
    }

    public User(Integer user_pk, String user_id, String password, String email, String nickname) {
        this.user_pk = user_pk;
        this.user_id = user_id;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public User(Integer user_pk, String nickname, String userId) {

        this.user_pk = user_pk;
        this.nickname = nickname;
        this.user_id = userId;
    }

    @Override
    public String toString() {
        return "User{" +
               "id='" + user_pk + '\'' +
               ", userId='" + user_id + '\'' +
               ", password='" + password + '\'' +
               ", email='" + email + '\'' +
               ", nick='" + nickname + '\'' +
               '}';
    }

    public int getUserPk() {
        return user_pk;
    }

    public String getUserId() {
        return user_id;
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
}
