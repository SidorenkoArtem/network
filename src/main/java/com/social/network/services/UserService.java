package com.social.network.services;

import com.social.network.exceptions.EmailAlreadyExistException;
import com.social.network.exceptions.LoginAlreadyExitstsException;
import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.PagePermission;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserDetail;
import com.social.network.model.dto.UserDto;
import com.social.network.model.requests.UserRequest;
import com.social.network.model.responces.*;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.social.network.ApplicationConstants.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GiftsService giftsService;
    private final SocialGroupsService socialGroupsService;
    private final UserFriendsService userFriendsService;

    @Transactional(readOnly = true)
    public PageResponses getCurrentUserPage() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        final Long userId = userDetail.getUserId();
        final PageResponses pageResponses = new PageResponses();
        final User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);

        pageResponses.setUser(ConvertUtil.convertToUserDto(user));

        final UserGiftsResponse gifts = giftsService.getOtherUserGift(userId, DEFAULT_OFFSET, DEFAULT_GIFTS_LIMIT);
        pageResponses.setGifts(gifts.getUserGifts());
        pageResponses.setCountGift(gifts.getCount());

        final SimpleSocialGroupsResponse groups = socialGroupsService.getSimpleSocialGroups(userId, DEFAULT_OFFSET, DEFAULT_SOCIAL_GROUPS_LIMIT);
        pageResponses.setSocialGroups(groups.getSocialGroups());
        pageResponses.setCountSocialGroups(groups.getCount());

        final UserFriendsResponse userFriend = userFriendsService.getOtherUserFriends(userId, DEFAULT_OFFSET, DEFAULT_USER_FRIENDS_LIMIT);
        pageResponses.setFriends(userFriend.getUserFriends());
        pageResponses.setCountFriends(userFriend.getCount());
        pageResponses.setCurrentUser(true);
        pageResponses.setAuthenticated(true);
        return pageResponses;
    }

    public void userRegistration(final UserRequest userRequest) {
        final User user = new User();
        if (userRepository.existsByLoginEquals(userRequest.getLogin())) {
            throw new LoginAlreadyExitstsException();
        }
        user.setLogin(userRequest.getLogin());
        user.setPassword(new BCryptPasswordEncoder()
                .encode(userRequest.getPassword()));
        user.setFirstName(userRequest.getFirstName());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setBirthday(userRequest.getBirthday());
        user.setSex(userRequest.getSex());
        if (userRepository.existsByEmailEquals(userRequest.getEmail())) {
            throw new EmailAlreadyExistException();
        }
        user.setEmail(userRequest.getEmail());
        user.setPhotoUrl(userRequest.getPhotoUrl());
        user.setRole(userRequest.getRole());
        user.setCountry(userRequest.getCountry());
        user.setCity(userRequest.getCity());
        userRepository.save(user);
        final PagePermission pagePermission = new PagePermission();
        pagePermission.setUserId(user.getId());

        pagePermission.setShowGroups(userRequest.getShowGroups());
        pagePermission.setShowWall(userRequest.getShowWall());
        pagePermission.setShowLocation(userRequest.getShowLocation());
        pagePermission.setShowGifts(userRequest.getShowGifts());
        pagePermission.setShowFriends(userRequest.getShowFriends());
        user.setPagePermission(pagePermission);
        userRepository.save(user);
    }

    public UserResponse updateUser(final UserRequest userRequest) {
        final Long userId = 6L;
        final User currentUser = userUpdate(userRequest, userId);
        return new UserResponse(ConvertUtil.convertToUserDto(currentUser));
    }

    public User userUpdate(final UserRequest userRequest, final Long userId) {
        final User currentUser = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        currentUser.setFirstName(userRequest.getFirstName());
        currentUser.setName(userRequest.getName());
        currentUser.setSurname(userRequest.getSurname());
        currentUser.setBirthday(userRequest.getBirthday());
        currentUser.setSex(userRequest.getSex());
        currentUser.setPhotoUrl(userRequest.getPhotoUrl());
        currentUser.setRole(userRequest.getRole());
        currentUser.setCountry(userRequest.getCountry());
        currentUser.setCity(userRequest.getCity());
        userRepository.save(currentUser);
        final PagePermission pagePermission = currentUser.getPagePermission();
        pagePermission.setShowGroups(userRequest.getShowGroups());
        pagePermission.setShowWall(userRequest.getShowWall());
        pagePermission.setShowLocation(userRequest.getShowLocation());
        pagePermission.setShowGifts(userRequest.getShowGifts());
        pagePermission.setShowFriends(userRequest.getShowFriends());
        currentUser.setPagePermission(pagePermission);
        userRepository.save(currentUser);
        return currentUser;
    }

    @Transactional(readOnly = true)
    public UserDto getUser(final Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        return ConvertUtil.convertToUserDto(user);
    }

    @Transactional(readOnly = true)
    public PageResponses getUserPage(final Long userId) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final PageResponses pageResponses = new PageResponses();
        final User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        final PagePermission pagePermission = user.getPagePermission();

        pageResponses.setUser(ConvertUtil.convertToUserDto(user));
        pageResponses.setAuthenticated(!authentication.getPrincipal().toString().equalsIgnoreCase("anonymousUser"));

        if (!pagePermission.getShowLocation()) {
            pageResponses.getUser().setCountry(null);
            pageResponses.getUser().setCity(null);
        }

        if (pagePermission.getShowGifts()) {
            final UserGiftsResponse gifts = giftsService.getOtherUserGift(userId, DEFAULT_OFFSET, DEFAULT_GIFTS_LIMIT);
            pageResponses.setGifts(gifts.getUserGifts());
            pageResponses.setCountGift(gifts.getCount());
        }

        if (pagePermission.getShowGroups()) {
            final SimpleSocialGroupsResponse groups = socialGroupsService.getSimpleSocialGroups(userId, DEFAULT_OFFSET, DEFAULT_SOCIAL_GROUPS_LIMIT);
            pageResponses.setSocialGroups(groups.getSocialGroups());
            pageResponses.setCountSocialGroups(groups.getCount());
        }

        if (pagePermission.getShowFriends()) {
            final UserFriendsResponse userFriend = userFriendsService.getOtherUserFriends(userId, DEFAULT_OFFSET, DEFAULT_USER_FRIENDS_LIMIT);
            pageResponses.setFriends(userFriend.getUserFriends());
            pageResponses.setCountFriends(userFriend.getCount());
        }
        pageResponses.setCurrentUser(false);
        return pageResponses;
    }

}