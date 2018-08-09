package com.social.network.services;

import com.social.network.ApplicationConstants;
import com.social.network.exceptions.SocialGroupNotExistException;
import com.social.network.model.dao.SocialGroup;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserGroup;
import com.social.network.model.dto.SimpleSocialGroupDto;
import com.social.network.model.dto.SimpleUserDto;
import com.social.network.model.dto.SocialGroupDto;
import com.social.network.model.responces.SimpleSocialGroupsResponse;
import com.social.network.model.responces.SocialGroupResponse;
import com.social.network.repositories.SocialGroupRepository;
import com.social.network.repositories.UserGroupRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final UserRepository userRepository;

    public SimpleSocialGroupsResponse getSimpleSocialGroups(final Long userId, final Integer page, final Integer limit) {
        final List<UserGroup> userGroups = userGroupRepository.findSocialGroupsByUserIdEquals(userId, PageRequest.of(page, limit, Sort.by(Sort.Order.desc("createTimestamp"))));
        final Set<Long> groupIds = userGroups.stream().map(UserGroup::getGroupId).filter(Objects::nonNull).collect(Collectors.toSet());
        final List<SocialGroup> socialGroups = socialGroupRepository.findSocialGroupsByIdIn(groupIds);
        final List<SimpleSocialGroupDto> socialGroupDtos = socialGroups.stream()
                .map(ConvertUtil::convertToSimpleSocialGroupDto).collect(Collectors.toList());
        return new SimpleSocialGroupsResponse(socialGroupDtos, groupIds.size());
    }

    public SocialGroupResponse getSocialGroup(final Long socialGroupId) {
        final SocialGroup socialGroup = socialGroupRepository.findById(socialGroupId)
                .orElseThrow(SocialGroupNotExistException::new);

        final SocialGroupDto socialGroupDto = ConvertUtil.convertToUserGroupDto(socialGroup);

        final List<UserGroup> userGroups = userGroupRepository.findUserGroupsByGroupIdEquals(socialGroupId,
                PageRequest.of(ApplicationConstants.DEFAULT_OFFSET, ApplicationConstants.DEFAULT_USER_GROUP_LIMIT));

        final Integer usersCount = userGroupRepository.countByGroupIdEquals(socialGroupId);

        final Set<Long> userIds = userGroups.stream().map(UserGroup::getUserId).collect(Collectors.toSet());
        final List<User> users = userRepository.findUsersByIdIn(userIds);
        final List<SimpleUserDto> userDtos = users.stream().map(ConvertUtil::convertToSimpleUserDto).collect(Collectors.toList());
        return new SocialGroupResponse(socialGroupDto, userDtos, usersCount);
    }
}
