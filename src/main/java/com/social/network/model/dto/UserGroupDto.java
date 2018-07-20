package com.social.network.model.dto;

import com.social.network.model.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserGroupDto {
    private final Long userId;
    private final Long groupId;
    private final Status status;
    private final LocalDateTime createTimestamp;
}
