package com.example.demo.Security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.demo.Security.ApplicationUserPermition.*;

public enum ApplicationUserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(SUBSCRIPTION_ADD, SUBSCRIPTION_VIEW, USER_READ, USER_WRITE));

    private final Set<ApplicationUserPermition> permission;

    ApplicationUserRole(Set<ApplicationUserPermition> permissions) {
        this.permission = permissions;
    }

    public Set<ApplicationUserPermition> getPermission() {
        return permission;
    }
}
