package com.wooruk.service;

import com.wooruk.domain.Category;
import com.wooruk.repository.PostDao;
import java.util.List;

public class PostService {

    private static PostService instance;
    private final PostDao postDao;

    private PostService() {
        postDao = PostDao.getInstance();
    }

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }

    public List<Category> getCategory() {
        return postDao.selectCategory();
    }

}
