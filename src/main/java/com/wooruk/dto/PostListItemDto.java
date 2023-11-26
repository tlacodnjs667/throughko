package com.wooruk.dto;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class PostListItemDto {

    private Integer post_pk;
    private String post_title;
    private String nickname;
    private String category;
    private LocalDateTime created_time;

    public PostListItemDto(Integer post_pk, String post_title, String nickname, String category,
        LocalDateTime created_time) {
        this.post_pk = post_pk;
        this.post_title = post_title;
        this.nickname = nickname;
        this.category = category;
        this.created_time = created_time;
    }

    public PostListItemDto(Integer post_pk, String post_title, String nickname, String category,
        Timestamp created_time) {
        this.post_pk = post_pk;
        this.post_title = post_title;
        this.nickname = nickname;
        this.category = category;
        this.created_time = created_time.toLocalDateTime();
    }


    public Integer getPostPk() {
        return post_pk;
    }

    public String getPostTitle() {
        return post_title;
    }

    public String getAuthor() {
        return nickname;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getCreatedTime() {
        return created_time;
    }

    public String getCreatedTimeForm() {
        int year = created_time.getYear();
        int month = created_time.getMonthValue();
        int dayOfMonth = created_time.getDayOfMonth();
        DayOfWeek day = created_time.getDayOfWeek();
        int hour = created_time.getHour();
        int minute = created_time.getMinute();
        int second = created_time.getSecond();

        return String.format("%d.%d.%d %s %d:%d:%d", year, month, day, dayOfMonth, hour, minute,
            second);
    }
}
