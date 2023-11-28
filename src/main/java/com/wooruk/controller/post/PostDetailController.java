package com.wooruk.controller.post;

import com.wooruk.domain.Post;
import com.wooruk.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "postDetailController", value = {"/post/detail"})
public class PostDetailController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(PostDetailController.class);
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String param = req.getParameter("postId");
        Integer postId = Integer.parseInt(param);

        Post post = postService.getPost(postId);
        log.debug("Controller : post={}", post);
        req.setAttribute("post", post);

        req.getRequestDispatcher("/WEB-INF/post/post_detail.jsp").forward(req, resp);
    }
}
