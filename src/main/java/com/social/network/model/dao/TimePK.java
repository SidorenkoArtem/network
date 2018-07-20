package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class TimePK implements Serializable {
    private final Long userId;
    private final Long friendId;
}