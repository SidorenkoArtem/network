package com.social.network.model.responces;

import com.social.network.model.dto.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class PostResponce {
    private final List<PostDto> posts;
    private final Integer count;
}
