package com.social.network.services;

import com.social.network.model.dao.SocialGroup;
import com.social.network.model.dao.UserGroup;
import com.social.network.model.dto.SimpleSocialGroupDto;
import com.social.network.model.responces.SimpleSocialGroupsResponse;
import com.social.network.repositories.SocialGroupRepository;
import com.social.network.repositories.UserGroupRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialGroupsService {

    private final SocialGroupRepository socialGroupRepository;
    private final UserGroupRepository userGroupRepository;

    public SimpleSocialGroupsResponse getSimpleSocialGroups() {
        final Long userId = 0L;//TODO
        final List<UserGroup> userGroups = userGroupRepository.findSocialGroupsByUserIdEquals(userId);
        final Set<Long> groupIds = userGroups.stream().map(UserGroup::getGroupId).filter(Objects::nonNull).collect(Collectors.toSet());
        final List<SocialGroup> socialGroups = socialGroupRepository.findSocialGroupsByIdIn(groupIds);
        final List<SimpleSocialGroupDto> socialGroupDtos = socialGroups.stream()
                .map(ConvertUtil::convertToSimpleSocialGroupDto).collect(Collectors.toList());
        return new SimpleSocialGroupsResponse(socialGroupDtos, userGroupRepository.countByUserIdEquals(userId));
    }

}
