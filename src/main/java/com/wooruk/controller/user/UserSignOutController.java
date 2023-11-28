package com.wooruk.controller.user;

import com.wooruk.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "userSignOutController", urlPatterns = {"/user/signout"})
public class UserSignOutController extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(UserSignUpController.class);
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        log.debug("::doGet");
        HttpSession session = req.getSession();

        session.removeAttribute("signedUser");
        session.invalidate();

        String param = "?action=logout";
        System.out.println(req.getContextPath());
        resp.sendRedirect(req.getContextPath() + param);
    }
}
