package com.wooruk.domain;

public class Category {

    private Integer category_pk;
    private String category_title;

    public Category(Integer category_pk, String category_title) {
        this.category_pk = category_pk;
        this.category_title = category_title;
    }

    public Integer getCategory_pk() {
        return category_pk;
    }

    public String getCategory_title() {
        return category_title;
    }
}
