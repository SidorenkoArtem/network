package com.social.network.model.requests;

import com.social.network.model.dao.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentsRequest {
    private final List<Comment> comments;
    private final Integer count;
}
