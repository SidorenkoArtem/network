package com.social.network.model.responces;

import com.social.network.model.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UsersResponse {
    private final List<UserDto> users;
    private final Integer count;
}
