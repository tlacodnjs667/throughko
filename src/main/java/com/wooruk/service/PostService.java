package com.wooruk.service;

import com.wooruk.controller.post.PostDetailController;
import com.wooruk.domain.Category;
import com.wooruk.domain.Post;
import com.wooruk.dto.PostCreateDto;
import com.wooruk.dto.PostListItemDto;
import com.wooruk.repository.PostDao;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostService {
    private final Logger log = LoggerFactory.getLogger(PostDetailController.class);
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

    public int createPost(PostCreateDto dto) {
        return postDao.createPost(dto);
    }

    public List<PostListItemDto> getPostList(Integer categoryFk) {
        return postDao.select(categoryFk);
    }

    public Post getPost(Integer postId) {
        postDao.read(postId);

        Post post = postDao.selectPostById(postId);

        String content = post.getPost_content();
        content = content.replace("\n", "<br>");
        post.setPost_content(content);
        log.debug("Controller : post={}", post);

        return post;
    }

    public int like (Integer postId ) {
        int result = postDao.like (postId);
        return result;
    }


}
