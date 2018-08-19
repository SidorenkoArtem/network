package com.social.network.model.responces;

import com.social.network.model.dto.SimpleUserDto;
import lombok.Data;
import java.util.List;

@Data
public class SimpleUsersResponse {
    private final List<SimpleUserDto> users;
}
