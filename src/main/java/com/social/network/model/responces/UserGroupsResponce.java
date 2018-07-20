package com.social.network.model.responces;

import com.social.network.model.dto.UserGroupDto;
import lombok.Data;

import java.util.List;

@Data
public class UserGroupsResponce {
    private final List<UserGroupDto> usergroups;
    private final Integer count;
}
