package com.social.network.model.responces;

import com.social.network.model.dto.SocialGroupDto;
import lombok.Data;
import java.util.List;

@Data
public class UserGroupsResponse {
    private final List<SocialGroupDto> userGroups;
    private final Integer count;
}
