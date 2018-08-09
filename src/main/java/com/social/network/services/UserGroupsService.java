package com.social.network.services;

import com.social.network.exceptions.RequestToGroupNotExistException;
import com.social.network.exceptions.SocialGroupNotExistException;
import com.social.network.exceptions.UserGroupNotExistException;
import com.social.network.model.dao.SocialGroup;
import com.social.network.model.dao.UserGroup;
import com.social.network.model.enums.Status;
import com.social.network.model.responces.SimpleSocialGroupsResponse;
import com.social.network.repositories.SocialGroupRepository;
import com.social.network.repositories.UserGroupRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGroupsService {

    private final UserGroupRepository userGroupRepository;
    private final SocialGroupRepository socialGroupRepository;

    @Transactional(readOnly = true)
    public SimpleSocialGroupsResponse getCurrentUserGroups(final Integer offset, final Integer limit) {
        final Long userId = 0L; //TODO
        return getUserGroups(userId, offset, limit);
    }

    @Transactional(readOnly = true)
    public SimpleSocialGroupsResponse getOtherUserGroups(final Long userId, final Integer page, final Integer limit) {
        return getUserGroups(userId, page, limit);
    }


    private SimpleSocialGroupsResponse getUserGroups(final Long userId,final Integer page, final Integer limit) {
        final List<UserGroup> userGroups = userGroupRepository.findSocialGroupsByUserIdEquals(userId, PageRequest.of(page, limit, Sort.by(Sort.Order.desc("createTimestamp"))));
        final List<Long> userGroupIds = userGroups.stream().map(UserGroup::getGroupId).collect(Collectors.toList());
        final List<SocialGroup> socialGroups = socialGroupRepository.findSocialGroupsByIdIn(userGroupIds);
        return new SimpleSocialGroupsResponse(socialGroups.stream().map(ConvertUtil::convertToSimpleSocialGroupDto)
                .collect(Collectors.toList()), userGroups.size());
    }

    @Transactional
    public void createRequestToGroup(final Long groupId) {
        final Long userId = 0L;
        final SocialGroup socialGroup = socialGroupRepository.findById(groupId).orElseThrow(SocialGroupNotExistException::new);
        final UserGroup userGroupRequest = new UserGroup();
        userGroupRequest.setGroupId(groupId);
        userGroupRequest.setUserId(userId);
        if (socialGroup.getPrivateGroup()) {
            userGroupRequest.setStatus(Status.REQUESTED);
        } else {
            userGroupRequest.setStatus(Status.APPROVED);
        }
        userGroupRepository.save(userGroupRequest);
    }

    @Transactional
    public void processUserToGroup(final Long groupId, final UserGroup request) {
        final UserGroup userGroup = userGroupRepository.findUserGroupByUserIdEqualsAndGroupIdEquals(request.getUserId(), groupId)
                .orElseThrow(RequestToGroupNotExistException::new);
        userGroup.setStatus(request.getStatus());
        userGroupRepository.save(userGroup);
    }

    @Transactional
    public void removeFromUserGroup(final Long groupId) {
        final Long userId = 0L;
        final UserGroup userGroup = userGroupRepository.findUserGroupByUserIdEqualsAndGroupIdEquals(userId, groupId)
                .orElseThrow(UserGroupNotExistException::new);
        userGroupRepository.delete(userGroup);
    }
}
