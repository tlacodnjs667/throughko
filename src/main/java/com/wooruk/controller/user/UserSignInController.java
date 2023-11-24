package com.wooruk.controller.user;

import com.wooruk.dto.UserInfoInSessionDto;
import com.wooruk.dto.UserSignInDto;
import com.wooruk.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "userSignInController", urlPatterns = {"/user/signin"})
public class UserSignInController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
    private UserService userService = UserService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/user/signin.jsp")
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.debug("::doPost()");
        String userId = req.getParameter("userid");
        String password = req.getParameter("password");

        UserSignInDto dto = new UserSignInDto(userId, password);
        log.debug("dto={}", dto);

        UserInfoInSessionDto userInDb = userService.signin(dto);
        log.debug("userInfoToSaveSession={}", userInDb);

        System.out.println(req.getParameter("target"));
        if (userInDb == null) {
            resp.sendRedirect(req.getContextPath() + "/user/signin?result=fail&target=" +
                URLEncoder.encode(req.getParameter("target"), StandardCharsets.UTF_8));
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("signedUser", userInDb);

            resp.sendRedirect(req.getParameter("target"));
        }
    }
}
