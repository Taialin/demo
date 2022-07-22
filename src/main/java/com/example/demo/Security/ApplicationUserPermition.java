package com.example.demo.Security;

public enum ApplicationUserPermition {
    USER_READ("student:read"),
    USER_WRITE("student:write"),
    SUBSCRIPTION_ADD("subscription:add"),
    SUBSCRIPTION_VIEW("subscription:view");



public final String permission;

    ApplicationUserPermition(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}


