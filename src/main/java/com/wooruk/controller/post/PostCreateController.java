package com.wooruk.controller.post;

import com.wooruk.dto.UserInfoInSessionDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "postCreateController", urlPatterns = {"/post/create"})
public class PostCreateController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(PostCreateController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {


        req.getRequestDispatcher("/WEB-INF/post/create_post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        HttpSession session = req.getSession();
        UserInfoInSessionDto userSigned = (UserInfoInSessionDto) session.getAttribute("signedUser");
        System.out.println(userSigned.toString());
    }
}
