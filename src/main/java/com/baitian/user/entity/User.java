package com.baitian.user.entity;

/**
 * @author cn
 * @date 2025-12-04 20:23
 */
public class User {
    private String name;


    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
