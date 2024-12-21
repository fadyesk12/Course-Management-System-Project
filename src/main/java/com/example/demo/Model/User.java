package com.example.demo.Model;

import lombok.Getter;

public abstract class User {

    private String role;
    public User() {}
    public abstract String getRole();
}
