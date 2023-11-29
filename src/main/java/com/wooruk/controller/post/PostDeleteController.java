package com.wooruk.controller.post;

import com.wooruk.dto.UserInfoInSessionDto;
import com.wooruk.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "postDeleteController", urlPatterns = {"/post/delete"})
public class PostDeleteController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(PostDetailController.class);
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String param = req.getParameter("postId");
        Integer postId = Integer.parseInt(param);
        Integer userId =
            ((UserInfoInSessionDto) req.getSession().getAttribute("signedUser")).getUserPk();

        boolean result = postService.deletePost(postId, userId);

        String paramClause = result ? "?delete=success" : "";
        String url = req.getContextPath() + "/post/list" + paramClause;

        if (result) {
            resp.sendRedirect(url);
        }

        if (result) {
            log.debug("포스트 삭제 성공");
        }
    }
}
