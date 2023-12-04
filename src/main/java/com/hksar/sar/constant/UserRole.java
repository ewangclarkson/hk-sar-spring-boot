package com.hksar.sar.constant;

public enum UserRole {
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
