package com.social.network.configuration;

import com.social.network.model.dao.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextHolder {
    public static Long userId () {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return userDetail.getUserId();
    }
}
