package com.social.network.model.requests;

import lombok.Data;

@Data
public class MessagesRequest {
    private final Long userId;
    private final String text;
}
