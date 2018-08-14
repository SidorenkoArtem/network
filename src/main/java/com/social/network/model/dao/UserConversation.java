package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_conversation")
public class UserConversation {
    @Id
    @SequenceGenerator(name = "conversation_seq", sequenceName = "conversation_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conversation_seq")
    private Long id;
    @Column(name = "creator_user_id")
    private Long creatorUserId;
    @Column(name = "companion_user_id")
    private Long companionUserId;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp;
}
