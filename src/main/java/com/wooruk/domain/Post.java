package com.wooruk.domain;

import com.wooruk.dto.UserForPostDto;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Post {
    private Logger log = LoggerFactory.getLogger(Post.class);

    private Integer post_pk;
    private String post_title;
    private String post_content;
    private UserForPostDto author;
    private Category category;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    private Integer likes;
    private Integer hits;

    public Post(Integer post_pk, String post_title, String post_content, UserForPostDto author,
        Category category, LocalDateTime created_time, LocalDateTime modified_time, Integer likes,
        Integer hits) {
        this.post_pk = post_pk;
        this.post_title = post_title;
        this.post_content = post_content;
        this.author = author;
        this.category = category;
        this.created_time = created_time;
        this.modified_time = modified_time;
        this.likes = likes;
        this.hits = hits;
    }

    public static PostBuilder getBuilder() {
        return new PostBuilder();
    }

    public Integer getPost_pk() {
        return post_pk;
    }

    public String getPost_title() {
        return post_title;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public UserForPostDto getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreated_time() {
        return created_time;
    }

    public String getCreated_time_string() {
        String result =  makeTimeStringFormat(created_time);
        log.debug(result);
        return result;
    }

    public LocalDateTime getModified_time() {
        return modified_time;
    }

    public String getModified_time_string() {
        return makeTimeStringFormat(modified_time);
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getHits() {
        return hits;
    }

    public String makeTimeStringFormat(LocalDateTime time) {
        return String.format("%d. %d. %d. %d:%d", time.getYear(), time.getMonthValue(),
            time.getDayOfMonth(), time.getHour(), time.getMinute());
    }

    public static class PostBuilder {

        private Integer post_pk;
        private String post_title;
        private String post_content;
        private UserForPostDto author;
        private Category category;
        private LocalDateTime created_time;
        private LocalDateTime modified_time;
        private Integer likes;
        private Integer hits;

        private PostBuilder() {
        }

        public PostBuilder post_pk(Integer post_pk) {
            this.post_pk = post_pk;
            return this;
        }

        public PostBuilder post_title(String post_title) {
            this.post_title = post_title;
            return this;
        }

        public PostBuilder post_content(String post_content) {
            this.post_content = post_content;
            return this;
        }

        public PostBuilder created_time(LocalDateTime created_time) {
            this.created_time = created_time;
            return this;
        }

        public PostBuilder created_time(Timestamp created_time) {
            this.created_time = created_time.toLocalDateTime();
            return this;
        }

        public PostBuilder modified_time(LocalDateTime modified_time) {
            this.modified_time = modified_time;
            return this;
        }

        public PostBuilder modified_time(Timestamp modified_time) {
            this.modified_time = modified_time.toLocalDateTime();
            return this;
        }

        public void setPost_content(String post_content) {
            this.post_content = post_content;
        }

        public PostBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public PostBuilder author(UserForPostDto author) {
            this.author = author;
            return this;
        }

        public PostBuilder hits(Integer hits) {
            this.hits = hits;
            return this;
        }

        public PostBuilder likes(Integer likes) {
            this.likes = likes;
            return this;
        }

        public Post build() {
            return new Post(post_pk, post_title, post_content, author, category, created_time,
                modified_time, likes, hits);
        }

    }
}