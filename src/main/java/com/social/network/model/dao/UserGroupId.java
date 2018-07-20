package com.social.network.model.dao;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class UserGroupId {
    private Long groupId;
    private Long userId;
}
