package com.social.network.model.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_gift")
@Data
public class UserGift {
    @Id
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "gift_id")
    private Long giftId;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
