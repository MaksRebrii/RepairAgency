package com.my.repairagency.repository.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private int id;
    private String title;

    public Role() {
    }

    public Role(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
