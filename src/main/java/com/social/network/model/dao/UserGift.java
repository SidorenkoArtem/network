package com.social.network.model.dao;

import com.social.network.model.enums.Status;
import lombok.Data;

import javax.persistence.*;
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
    @Column(name = "gift_user_from_id")
    private Long giftFromId;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
    @Column(columnDefinition = "enum('APPROVED', 'BLOCKED', 'REQUESTED')")
    @Enumerated(EnumType.STRING)
    private Status status;
}
