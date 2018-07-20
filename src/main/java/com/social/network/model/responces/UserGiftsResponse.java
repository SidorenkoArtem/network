package com.social.network.model.responces;

import com.social.network.model.dto.UserGiftDto;
import lombok.Data;

import java.util.List;

@Data
public class UserGiftsResponse {
    private final List<UserGiftDto> userGifts;
    private final Integer count;
}
