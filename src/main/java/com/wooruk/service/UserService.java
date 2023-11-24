package com.wooruk.service;

import com.wooruk.domain.User;
import com.wooruk.dto.UserInfoInSessionDto;
import com.wooruk.dto.UserSignInDto;
import com.wooruk.dto.UserSignUpDto;
import com.wooruk.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static UserService instance;
    private static Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserDao userDao;

    private UserService() {
        userDao = UserDao.getInstance();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public int signup(UserSignUpDto userToSign) {
        log.debug("userService::signup()={}", userToSign);
        int result = userDao.signup(userToSign);
        return result;
    }

    public UserInfoInSessionDto signin(UserSignInDto dto) {
        User result = userDao.signin(dto);

        UserInfoInSessionDto signedUser = UserInfoInSessionDto.valueOf(result);
        return signedUser;
    }


}