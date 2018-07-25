package com.social.network.model.responces;

import com.social.network.model.dto.UserDto;
import lombok.Data;
import java.util.List;

@Data
public class SimpleUsersResponse {
    private final List<UserDto> users;
}
