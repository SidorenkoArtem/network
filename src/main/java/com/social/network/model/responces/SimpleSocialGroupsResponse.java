package com.social.network.model.responces;

import com.social.network.model.dto.SimpleSocialGroupDto;
import lombok.Data;

import java.util.List;

@Data
public class SimpleSocialGroupsResponse {
    private final List<SimpleSocialGroupDto> socialGroups;
    private final Integer count;
}
