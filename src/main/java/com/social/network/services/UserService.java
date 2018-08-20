package com.social.network.services;

import com.social.network.configuration.ContextHolder;
import com.social.network.exceptions.LoginAlreadyExitstsException;
import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.PagePermission;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserFriends;
import com.social.network.model.dto.UserDto;
import com.social.network.model.enums.Status;
import com.social.network.model.requests.UserRequest;
import com.social.network.model.responces.*;
import com.social.network.repositories.UserFriendsRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.social.network.ApplicationConstants.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GiftsService giftsService;
    private final SocialGroupsService socialGroupsService;
    private final UserFriendsService userFriendsService;
    private final UserFriendsRepository userFriendsRepository;

    @Transactional(readOnly = true)
    public SimpleUsersResponse getUsers(final String name, final String firstName, final String city) {
        final Long currentUserId = ContextHolder.userId();
        final List<User> users = userRepository.findUsersByNameContainingAndFirstNameContainingAndCityContaining(name, firstName, city);
        final Map<Long, Boolean> userIdAndIsFriendMap = users.stream()
                .collect(Collectors.toMap(User::getId, e -> {
                    final Long userId = e.getId();
                    final UserFriends relationship = userFriendsRepository.getRelationship(userId, currentUserId)
                            .orElse(new UserFriends());
                    if (relationship != null && relationship.getStatus().equals(Status.APPROVED)) {
                        return true;
                    } else {
                        return false;
                    }
                }));
        return new SimpleUsersResponse(users.stream().map(e -> {
                    final Long userId = e.getId();
                    return ConvertUtil.convertToSimpleUserDto(e, userIdAndIsFriendMap.getOrDefault(userId, false));
                }).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public PageResponses getCurrentUserPage() {
        final Long userId = ContextHolder.userId();
        final PageResponses pageResponses = new PageResponses();
        final User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);

        pageResponses.setUser(ConvertUtil.convertToUserDto(user));
        pageResponses.getUser().setShowWall(true);
        final UserGiftsResponse gifts = giftsService.getOtherUserGift(userId, DEFAULT_OFFSET, DEFAULT_GIFTS_LIMIT);
        pageResponses.setGifts(gifts.getUserGifts());
        pageResponses.setCountGift(gifts.getCount());

        final SimpleSocialGroupsResponse groups = socialGroupsService.getSimpleSocialGroups(userId, DEFAULT_OFFSET, DEFAULT_SOCIAL_GROUPS_LIMIT);
        pageResponses.setSocialGroups(groups.getSocialGroups());
        pageResponses.setCountSocialGroups(groups.getCount());

        final UserFriendsResponse userFriend = userFriendsService.getOtherUserFriends(userId, DEFAULT_OFFSET, DEFAULT_USER_FRIENDS_LIMIT);
        pageResponses.setFriends(userFriend.getUserFriends());
        pageResponses.setCountFriends(userFriend.getCount());

        pageResponses.setShowBirthday(true);
        pageResponses.setShowSex(true);
        pageResponses.setCurrentUser(true);
        pageResponses.setAuthenticated(true);
        pageResponses.setShowPage(true);
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
        pagePermission.setShowPage(userRequest.getShowPage());
        pagePermission.setShowSex(userRequest.getShowSex());
        pagePermission.setShowBirthday(userRequest.getShowBirthday());
        user.setPagePermission(pagePermission);
        userRepository.save(user);
    }

    public void updateUser(final UserRequest userRequest) {
        final Long userId = ContextHolder.userId();
        userUpdate(userRequest, userId);
    }

    private void userUpdate(final UserRequest userRequest, final Long userId) {
        final User currentUser = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        currentUser.setFirstName(userRequest.getFirstName());
        currentUser.setName(userRequest.getName());
        currentUser.setSurname(userRequest.getSurname());
        currentUser.setPhotoUrl(userRequest.getPhotoUrl());
        userRepository.save(currentUser);
        final PagePermission pagePermission = currentUser.getPagePermission();
        pagePermission.setShowGroups(userRequest.getShowGroups());
        pagePermission.setShowWall(userRequest.getShowWall());
        pagePermission.setShowLocation(userRequest.getShowLocation());
        pagePermission.setShowGifts(userRequest.getShowGifts());
        pagePermission.setShowFriends(userRequest.getShowFriends());
        pagePermission.setShowPage(userRequest.getShowPage());
        pagePermission.setShowSex(userRequest.getShowSex());
        pagePermission.setShowBirthday(userRequest.getShowBirthday());
        currentUser.setPagePermission(pagePermission);
        userRepository.save(currentUser);
    }

    @Transactional(readOnly = true)
    public UserDto getUser(final Long userId) {
        final User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        return ConvertUtil.convertToUserDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto getCurrentUser() {
        final User user = userRepository.findById(ContextHolder.userId())
                .orElseThrow(UserNotExistsException::new);
        return ConvertUtil.convertToUserDto(user);
    }

    @Transactional(readOnly = true)
    public PageResponses getUserPage(final Long userId) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final PageResponses pageResponses = new PageResponses();
        final User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);
        final PagePermission pagePermission = user.getPagePermission();
        final boolean anonymous = authentication.getPrincipal().toString().equalsIgnoreCase("anonymousUser");
        pageResponses.setUser(ConvertUtil.convertToUserDto(user));
        pageResponses.setAuthenticated(!anonymous);

        if (!anonymous) {
            final Long currentUserId = ContextHolder.userId();
            final UserFriends relationship = userFriendsRepository.getRelationship(userId, currentUserId).orElse(null);
            pageResponses.setHasRequestOnFriendship(relationship != null);
            if (relationship != null) {
                pageResponses.setFriend(relationship.getStatus().equals(Status.APPROVED));
            }
        }

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
        pageResponses.setShowPage(pagePermission.getShowPage());
        pageResponses.setShowLocation(pagePermission.getShowLocation());
        pageResponses.setShowBirthday(pagePermission.getShowBirthday());
        pageResponses.setShowSex(pagePermission.getShowSex());
        pageResponses.setCurrentUser(false);
        return pageResponses;
    }

}