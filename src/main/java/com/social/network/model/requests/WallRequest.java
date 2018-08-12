package com.social.network.model.requests;

import lombok.Data;

@Data
public class WallRequest {
    private final String fileUrl;
    private final String text;
}
