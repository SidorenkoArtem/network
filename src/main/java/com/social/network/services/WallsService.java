package com.social.network.services;

import com.social.network.configuration.ContextHolder;
import com.social.network.exceptions.PostNotExistsException;
import com.social.network.exceptions.UserNotExistsException;
import com.social.network.exceptions.UserNotOwnerException;
import com.social.network.model.dao.User;
import com.social.network.model.dao.WallPost;
import com.social.network.model.dto.WallPostDto;
import com.social.network.model.requests.WallRequest;
import com.social.network.model.responces.WallPostResponse;
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

    public WallPostResponse createWallPost(final Long wallOwnerId, final WallRequest wallRequest) {
        final Long userId = ContextHolder.userId();
        final WallPost wallPost = new WallPost();
        wallPost.setText(wallRequest.getText());
        wallPost.setWallOwnerId(wallOwnerId);
        wallPost.setUserId(userId);
        wallRepository.save(wallPost);
        final User user = userRepository.findById(wallPost.getUserId()).orElseThrow(UserNotExistsException::new);
        return new WallPostResponse(ConvertUtil.convertToWallPostDto(wallPost, user));
    }

    public WallPostsResponse getWallPosts(final Long wallOwnerId, final Integer page, final Integer limit) {
        final List<WallPost> posts = wallRepository.findWallPostsByWallOwnerIdEquals(
                wallOwnerId, PageRequest.of(page, limit, Sort.by(Sort.Order.desc("createTimestamp"))));
        final Set<Long> userIds = posts.stream().map(WallPost::getUserId).collect(Collectors.toSet());
        final Map<Long, User> userUdAndUserMap = userRepository.findUsersByIdIn(userIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));
        return new WallPostsResponse(posts.stream().map(e -> {
            final Long userId = e.getUserId();
            return ConvertUtil.convertToWallPostDto(e, userUdAndUserMap.getOrDefault(userId, new User()));
        }).collect(Collectors.toList()), wallRepository.countWallPostsByWallOwnerIdEquals(wallOwnerId));
    }

    public void deleteWallPost(final Long postId) {
        final Long userId = ContextHolder.userId();
        final WallPost wallPost = wallRepository.findById(postId)
                .orElseThrow(PostNotExistsException::new);
        if (!wallPost.getWallOwnerId().equals(userId)) {
            throw new UserNotOwnerException();
        }
        wallRepository.delete(wallPost);
    }
}
