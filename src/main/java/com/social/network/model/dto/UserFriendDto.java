package com.social.network.model.dto;

import lombok.Data;

@Data
public class UserFriendDto {
    private Long userId;
    private SimpleUserDto friend;
}