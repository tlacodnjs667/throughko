package com.wooruk.controller.post;

import com.wooruk.dto.PostCreateDto;
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

@WebServlet(name = "postCreateController", urlPatterns = {"/post/create"})
public class PostCreateController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(PostCreateController.class);
    private final PostService service = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/post/create_post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        UserInfoInSessionDto userSigned = (UserInfoInSessionDto) req.getSession()
            .getAttribute("signedUser");

        Integer userPk = userSigned.getUserPk();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Integer category_pk = Integer.parseInt(req.getParameter("category"));

        log.debug("category_pk={}", category_pk);
        PostCreateDto dto = new PostCreateDto(userPk, title, content, category_pk);

        int result = service.createPost(dto);

        if (result == 1) {
            resp.sendRedirect(req.getContextPath());
        } else  {
            String url = req.getContextPath() + "/post/create?result=fail" ;
            resp.sendRedirect(url);
        }
    }
}
