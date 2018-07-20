package com.social.network.model.dao;

import com.social.network.model.enums.Status;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_friends")
@Data
@IdClass(TimePK.class)
public class UserFriends {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "friend_id")
    private Long friendId;
    @Column(columnDefinition = "enum('APPROVED', 'BLOCKED', 'REQUESTED')")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
