package com.social.network.services;

import com.social.network.model.dao.Gift;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserGift;
import com.social.network.model.dto.UserGiftDto;
import com.social.network.model.responces.GiftsResponse;
import com.social.network.model.responces.UserGiftsResponse;
import com.social.network.repositories.GiftRepository;
import com.social.network.repositories.UserGiftRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftsService {

    private final GiftRepository giftRepository;
    private final UserGiftRepository userGiftRepository;
    private final UserRepository userRepository;

    public UserGiftsResponse getCurrentUserGift(final Integer offset, final Integer limit) {
        final Long userId = 0L;//TODO
        return getUserGift(userId, offset, limit);
    }

    public UserGiftsResponse getOtherUserGift(final Long userId, final Integer offset, final Integer limit) {
        return getUserGift(userId, offset, limit);
    }

    private UserGiftsResponse getUserGift(final Long userId, final Integer offset, final Integer limit) {
        final List<UserGift> userGifts = userGiftRepository.findUserGiftByUserIdEquals(userId, PageRequest.of(offset, limit));
        final Set<Long> giftIds = userGifts.stream().map(UserGift::getGiftId).collect(Collectors.toSet());
        final List<Gift> gifts = giftRepository.findGiftByIdIn(giftIds);
        final Map<Long, Gift> giftIdAndGiftMap = gifts.stream().collect(Collectors.toMap(Gift::getId, Function.identity()));
        final Set<Long> userIdsGiftFrom = userGifts.stream().map(UserGift::getGiftFromId).collect(Collectors.toSet());
        final List<User> usersGiftFrom = userRepository.findUsersByIdIn(userIdsGiftFrom);
        final Map<Long, User> userIdAndUserMap = usersGiftFrom.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        final List<UserGiftDto> userGiftDtos = userGifts.stream().map(e -> {
            final Long giftId = e.getGiftId();
            final Long userIdGiftFrom = e.getGiftFromId();
            final Gift gift = giftIdAndGiftMap.get(giftId);
            final User user = userIdAndUserMap.get(userIdGiftFrom);
            return ConvertUtil.convertToUserGiftDto(e, gift, user);
        }).collect(Collectors.toList());
        return new UserGiftsResponse(userGiftDtos, userGiftRepository.countByUserIdEquals(userId));
    }
}
