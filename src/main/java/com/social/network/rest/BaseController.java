package com.social.network.rest;

import com.social.network.model.dao.User;

public interface BaseController {

    default void checkStandardUserAuthenticationOptions() {

    }

    default void checkValidRolesAccess(User... roles) {

    }

    default void checkIllegalRolesAccess(User... roles) {

    }

    default void checkThatUserActive() {

    }

    default void checkThatUserAuthenticated() {

    }

    default void checkThatUserAdmin() {
    }
}
