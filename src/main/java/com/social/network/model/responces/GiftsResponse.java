package com.social.network.model.responces;

import com.social.network.model.dto.GiftDto;
import lombok.Data;

import java.util.List;

@Data
public class GiftsResponse {
    private final List<GiftDto> gifts;
    private final Integer count;
}
