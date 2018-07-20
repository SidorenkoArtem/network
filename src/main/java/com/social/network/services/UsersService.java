package com.social.network.services;

import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.User;
import com.social.network.model.dto.SocialGroupDto;
import com.social.network.model.responces.SocialGroupResponse;
import com.social.network.model.responces.UserResponse;
import com.social.network.model.responces.UsersResponse;
import com.social.network.repositories.SocialGroupRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;
    private final SocialGroupRepository socialGroupRepository;

    public SocialGroupResponse getUserSocialGroups(final Long userId) {
        //final List<UserGroup> socialGroups = socialGroupRepository.
        return new SocialGroupResponse(null, null);
    }

    public UsersResponse getUsers(final String searchText) {
        return new UsersResponse(null, null);
    }

    public UserResponse getUser(final Long id) {
        final User user = userRepository.findById(id).orElseThrow(UserNotExistsException::new);
        return new UserResponse(ConvertUtil.convertToUserDto(user));
    }
}
