package com.social.network.services;

import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.PagePermission;
import com.social.network.model.dao.User;
import com.social.network.model.requests.UserRequest;
import com.social.network.model.responces.UserResponse;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void userRegistration(final UserRequest userRequest) {
        final User user = new User();
        user.setLogin(userRequest.getLogin());//todo check on existing login
        user.setPassword(userRequest.getPassword());
        user.setFirstName(userRequest.getFirstName());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setBirthday(userRequest.getBirthday());
        user.setSex(userRequest.getSex());
        user.setEmail(userRequest.getEmail());
        user.setPhotoUrl(userRequest.getPhotoUrl());
        user.setRole(userRequest.getRole());
        userRepository.save(user);
        final PagePermission pagePermission = new PagePermission();
        pagePermission.setUserId(user.getId());
        pagePermission.setShowWall(userRequest.getShowWall());
        pagePermission.setShowLocation(userRequest.getShowLocation());
        pagePermission.setShowGift(userRequest.getShowGift());
        user.setPagePermission(pagePermission);
        userRepository.save(user);
    }

    @Transactional
    public UserResponse updateUser(final UserRequest userRequest) {
        final Long userId = 0L;
        final User currentUser = userUpdate(userRequest, userId);
        return new UserResponse(ConvertUtil.convertToUserDto(currentUser));
    }

    @Transactional
    public User userUpdate(final UserRequest userRequest, final Long userId) {
        final User currentUser = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        currentUser.setLogin(userRequest.getLogin());//todo check on existing login
        currentUser.setPassword(userRequest.getPassword());
        currentUser.setFirstName(userRequest.getFirstName());
        currentUser.setName(userRequest.getName());
        currentUser.setSurname(userRequest.getSurname());
        currentUser.setBirthday(userRequest.getBirthday());
        currentUser.setSex(userRequest.getSex());
        currentUser.setEmail(userRequest.getEmail());
        currentUser.setPhotoUrl(userRequest.getPhotoUrl());
        currentUser.setRole(userRequest.getRole());
        final PagePermission pagePermission = currentUser.getPagePermission();
        pagePermission.setShowWall(userRequest.getShowWall());
        pagePermission.setShowLocation(userRequest.getShowLocation());
        pagePermission.setShowGift(userRequest.getShowGift());
        currentUser.setPagePermission(pagePermission);
        userRepository.save(currentUser);
        return currentUser;
    }
}
