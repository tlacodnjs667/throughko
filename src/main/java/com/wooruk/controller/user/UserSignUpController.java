package com.wooruk.controller.user;

import com.wooruk.dto.UserSignUpDto;
import com.wooruk.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "userSignUpController", urlPatterns = {"/user/signup"})
public class UserSignUpController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/user/signup.jsp")
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String userId = req.getParameter("userid");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");

        UserSignUpDto dto = new UserSignUpDto(userId, password, email, nickname);
        log.debug("doPost(dto={})", dto);
        int result = userService.signup(dto);

        if (result == 1) {
            log.debug("유저 생성 성공, 로그인하세요");
            resp.sendRedirect(req.getContextPath());
        } else {
            log.debug("유저 생성 실패");
            String failStatus = "?result=fail";
            resp.sendRedirect(req.getContextPath() + "/user/signup" + failStatus);
        }

    }
}
