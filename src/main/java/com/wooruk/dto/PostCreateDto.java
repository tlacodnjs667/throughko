package com.wooruk.dto;

public class PostCreateDto {
    private Integer user_fk;
    private String post_title;
    private String post_content;
    private Integer category_fk;

    public PostCreateDto(Integer user_fk, String post_title, String post_content, Integer category_fk) {
        this.user_fk = user_fk;
        this.post_title = post_title;
        this.post_content = post_content;
        this.category_fk = category_fk;
    }

    public Integer getUserFk() {
        return user_fk;
    }

    public String getPostTitle() {
        return post_title;
    }

    public String getPostContent() {
        return post_content;
    }

    public Integer getCategoryFk() {
        return category_fk;
    }
}
