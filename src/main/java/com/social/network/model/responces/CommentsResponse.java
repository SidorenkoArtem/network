package com.social.network.model.responces;

import com.social.network.model.dto.CommentDto;
import lombok.Data;

import java.util.List;

@Data
public class CommentsResponse {
    private final List<CommentDto> comments;
    private final Integer count;
}
