package com.wooruk.controller.user;

import com.wooruk.domain.User;
import com.wooruk.dto.UserInfoInSessionDto;
import com.wooruk.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "userProfileController", urlPatterns = {"/user/profile"})
public class UserProfileController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(UserProfileController.class);
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.debug("doGet");
        UserInfoInSessionDto signedUser
            = (UserInfoInSessionDto) req.getSession().getAttribute("signedUser");

        int userPk = signedUser.getUserPk();

        User user = userService.getUserInfo(userPk);
        log.debug("userInfo={}", user);
        req.setAttribute("userInfo", user);
        req.getRequestDispatcher("/WEB-INF/user/user_profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
