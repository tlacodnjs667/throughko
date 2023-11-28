package com.wooruk.controller.post;

import com.wooruk.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "postLikeController", urlPatterns = {"/post/like"})
public class PostLikeController extends HttpServlet {


    private final Logger log = LoggerFactory.getLogger(PostDetailController.class);
    private final PostService postService = PostService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String param = req.getParameter("postId");
        Integer postId = Integer.valueOf(param);

        int result = postService.like(postId);

        String url = "/post/detail?postId=" + postId;
        resp.sendRedirect(req.getContextPath()+url);
    }
}
