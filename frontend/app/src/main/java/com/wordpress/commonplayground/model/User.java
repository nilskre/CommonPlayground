package com.wordpress.commonplayground.model;

public class User {
    private Long id;
    private String name;

    User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
