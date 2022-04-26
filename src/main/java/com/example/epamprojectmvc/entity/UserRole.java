package com.example.epamprojectmvc.entity;

public enum UserRole {
    ADMIN(1, "admin"),
    CUSTOMER(2, "customer");

    private int role_id;
    private String name;

    UserRole() {
    }

    UserRole(int role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getName() {
        return name;
    }
}
