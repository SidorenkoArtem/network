package com.social.network.model.responces;

import com.social.network.model.dto.SocialGroupDto;
import lombok.Data;

import java.util.List;

@Data
public class SocialGroupResponse {
    private final List<SocialGroupDto> socialGroups;
    private final Integer count;
}
