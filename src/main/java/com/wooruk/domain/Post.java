package com.wooruk.domain;

import java.time.LocalDateTime;

public class Post {
    private Integer post_pk;
    private String post_title;
    private String post_content;
    private User author;
    private Category category;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    private Integer likes;
    private Integer hits;

}
