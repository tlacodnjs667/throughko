package com.wooruk.dto;

import com.wooruk.domain.Post;

public class PostUpdateDto {

    private Integer postId;
    private String title;
    private String content;

    public PostUpdateDto(Integer postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Post toPost() {
        return new Post(postId, title, content);
    }
}
