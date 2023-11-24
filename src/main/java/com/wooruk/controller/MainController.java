package com.wooruk.controller;

import com.wooruk.domain.Category;
import com.wooruk.service.PostService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "mainController", urlPatterns = {""})
public class MainController extends HttpServlet {

    private PostService postService = PostService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        ServletContext application = req.getServletContext();

        if (application.getAttribute("categories") == null) {
            List<Category> categories = postService.getCategory();
            application.setAttribute("categories", categories);
        }

        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
