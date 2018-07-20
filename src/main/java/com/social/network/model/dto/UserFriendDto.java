package com.social.network.model.dto;

import com.social.network.model.enums.Status;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserFriendDto {
    private Long userId;
    private Long friendId;
    private Status status;
    private LocalDateTime createTimestamp;
}