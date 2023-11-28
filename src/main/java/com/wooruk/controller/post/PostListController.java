package com.wooruk.controller.post;

import com.wooruk.domain.Category;
import com.wooruk.dto.PostListItemDto;
import com.wooruk.service.PostService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "postListController", urlPatterns = {"/post/list"})
public class PostListController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(PostListController.class);
    private final PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Integer category_fk;
        String category = req.getParameter("category_id");
        String order = req.getParameter("order");

        if (category != null) {
            category_fk = Integer.parseInt(category);
        } else {
            category_fk = null;
        }

        List<PostListItemDto> postListItems = postService.getPostList(category_fk);

        String listDescription = "전체 목록 보기";

        if (category == null) {
            req.setAttribute("listDescription", listDescription);
        } else {
            ServletContext application = req.getServletContext();

            List<Category> categories = (List<Category>) application.getAttribute("categories");

            Category matched_category = categories.stream()
                .filter(e -> e.getCategory_pk() == category_fk).toList().get(0);

            req.setAttribute("listDescription",
                matched_category.getCategory_title() + "  카테고리 내 게시글 리스트");
        }

        req.setAttribute("postListItems", postListItems);
        log.debug("::doGet()" + postListItems.toString());

        req.getRequestDispatcher("/WEB-INF/post/post_list.jsp").forward(req, resp);
    }

}
