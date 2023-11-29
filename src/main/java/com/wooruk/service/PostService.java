package com.wooruk.service;

import com.wooruk.controller.post.PostDetailController;
import com.wooruk.domain.Category;
import com.wooruk.domain.Post;
import com.wooruk.dto.PostCreateDto;
import com.wooruk.dto.PostListItemDto;
import com.wooruk.dto.PostUpdateDto;
import com.wooruk.repository.PostDao;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostService {

    private static PostService instance;
    private final Logger log = LoggerFactory.getLogger(PostDetailController.class);
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

    public List<PostListItemDto> getPostList(Integer categoryFk, String order) {
        String orderClause = order != null ? "LIKES DESC, " : "";
        return postDao.select(categoryFk, orderClause);
    }

    public Post getPost(Integer postId) {
        postDao.read(postId);

        Post post = postDao.selectPostById(postId);

        String content = post.getPost_content();
        content = content.replace("\n", "<br/>");
        post.setPost_content(content);
        log.debug("Controller : post={}", post);

        return post;
    }

<<<<<<< HEAD
    public Post getPostToUpdate(Integer postId) {
        Post post = postDao.selectPostById(postId);
        log.debug("Controller : post={}", post);

        return post;
    }

    public int like (Integer postId) {
        int result = postDao.like (postId);
=======
    public int like(Integer postId) {
        int result = postDao.like(postId);
>>>>>>> c4265c3c1f75517f3a89b1f4cd21c08e0a4a1341
        return result;
    }

    public boolean deletePost(Integer postId, Integer userId) {
        boolean result;
        if (postDao.selectPostByUserAndPost(postId, userId)) {
            result = true;
        } else {
            result = false;
        }
        result = postDao.deletePost(postId);
<<<<<<< HEAD
=======

        return result;
    }
>>>>>>> c4265c3c1f75517f3a89b1f4cd21c08e0a4a1341

        return result;
    }

    public int updatePost(PostUpdateDto dto) {
        int result = postDao.updatePost(dto);

        return result;
    }
}
