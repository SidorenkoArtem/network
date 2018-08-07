package com.social.network.model.responces;

import lombok.Data;

@Data
public class ApplicationErrorResponse {

    private final int errorCode;
    private final String message;

}
