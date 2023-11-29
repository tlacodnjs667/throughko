package com.wooruk.controller.post;

import com.wooruk.domain.Post;
import com.wooruk.dto.PostUpdateDto;
import com.wooruk.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "postUpdateController", urlPatterns = {"/post/update"})
public class PostUpdateController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(PostListController.class);
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String parameter = req.getParameter("postId");
        int postId = Integer.parseInt(parameter);

        Post post = postService.getPostToUpdate(postId);
        req.setAttribute("post", post);

        req.getRequestDispatcher("/WEB-INF/post/post_update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.debug("::doPost");

        Integer postId = Integer.parseInt(req.getParameter("postId"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        PostUpdateDto dto = new PostUpdateDto(postId, title, content);

        int result = postService.updatePost(dto);
        
        String redirectUrl;

        if (result == 1) {
            String params = "postId=" + postId + "&action=update";
            redirectUrl = req.getContextPath() + "/post/detail?" + params;
        } else {
            String params = "postId=" + postId + "&result=fail";
            redirectUrl = req.getContextPath() + "/post/update?" + params;
        }
        
        resp.sendRedirect(redirectUrl);
    }
}
