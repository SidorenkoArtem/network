package com.social.network.model.requests;

import lombok.Data;

@Data
public class RegistrationRequest {
    private final String login;
    private final String password;
    private final String name;
    private final String firstName;
    private final String email;
}
