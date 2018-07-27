package com.social.network.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Messages {
    @Id
    private Long id;
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    private String text;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp;
}
