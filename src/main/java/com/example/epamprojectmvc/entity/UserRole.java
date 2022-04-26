package com.example.epamprojectmvc.entity;

public enum UserRole {
    ADMIN(1),
    CUSTOMER(2);

    private int role_id;

    UserRole() {
    }

    UserRole(int role_id) {
        this.role_id = role_id;
    }

}
