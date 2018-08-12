package com.social.network.services;

import com.social.network.model.dao.User;
import com.social.network.model.dao.WallPost;
import com.social.network.model.requests.WallRequest;
import com.social.network.model.responces.WallPostsResponse;
import com.social.network.repositories.UserRepository;
import com.social.network.repositories.WallRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WallsService {

    private final WallRepository wallRepository;
    private final UserRepository userRepository;

    public void createWallPost(final Long wallOwnerId, final WallRequest wallRequest) {
        final Long userId = 6L; //todo from Security context holder
        final WallPost wallPost = new WallPost();
        wallPost.setText(wallRequest.getText());
        wallPost.setFileUrl(wallRequest.getFileUrl());
        wallPost.setWallOwnerId(wallOwnerId);
        wallPost.setUserId(userId);
        wallRepository.save(wallPost);
    }

    public WallPostsResponse getWallPosts(final Long wallOwnerId, final Integer page, final Integer limit) {
        final List<WallPost> posts = wallRepository.findWallPostsByWallOwnerIdEquals(
                wallOwnerId, PageRequest.of(page, limit, Sort.by(Sort.Order.desc("createTimestamp"))));
        final Set<Long> userIds = posts.stream().map(WallPost::getUserId).collect(Collectors.toSet());
        final Map<Long, User> userUdAndUserMap = userRepository.findUsersByIdIn(userIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));
        return new WallPostsResponse(posts.stream().map(e ->{
                    final Long userId = e.getUserId();
                    return ConvertUtil.convertToWallPostDto(e, userUdAndUserMap.getOrDefault(userId, new User()));
                }).collect(Collectors.toList()),
            wallRepository.countWallPostsByWallOwnerIdEquals(wallOwnerId));
    }
}
