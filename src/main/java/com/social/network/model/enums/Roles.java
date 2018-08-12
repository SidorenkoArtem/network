package com.social.network.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ADMIN, USER, NONE;

    @Override
    public String getAuthority() {
        return name();
    }
}
