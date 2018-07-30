package com.social.network.model.requests;

import lombok.Data;

@Data
public class MessageRequest {
    private final String text;
    private final String fileUrl;
}
