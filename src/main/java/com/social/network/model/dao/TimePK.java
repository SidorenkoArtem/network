package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class TimePK implements Serializable {
    private final Long user_id;
    private final Long friend_id;
}