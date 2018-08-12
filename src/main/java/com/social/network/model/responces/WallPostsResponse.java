package com.social.network.model.responces;

import com.social.network.model.dto.WallPostDto;
import lombok.Data;

import java.util.List;

@Data
public class WallPostsResponse {
    private final List<WallPostDto> posts;
    private final Integer count;
}
