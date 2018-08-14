package com.social.network.model.responces;

import com.social.network.model.dto.SimpleUserDto;
import com.social.network.model.dto.SocialGroupDto;
import lombok.Data;

import java.util.List;

@Data
public class SocialGroupResponse {
    private final SocialGroupDto socialGroup;
    private final List<SimpleUserDto> users;
    private final Integer usersCount;
    private final Boolean subscriber;
}
