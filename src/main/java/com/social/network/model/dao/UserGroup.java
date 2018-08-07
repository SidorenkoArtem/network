package com.social.network.model.dao;

import com.social.network.model.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_group")
@IdClass(UserGroupId.class)
public class UserGroup {
    @Id
    @Column(name = "group_id")
    private Long groupId;
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(columnDefinition = "enum('APPROVED', 'BLOCKED', 'REQUESTED')")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
