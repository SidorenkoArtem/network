package com.social.network.model.requests;

import com.social.network.model.enums.Status;
import lombok.Data;

@Data
public class UserGroupRequest {
    private Status status;
    private Long userId;
}
