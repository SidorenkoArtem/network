package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserGroupId implements Serializable {
    private Long groupId;
    private Long userId;
}
