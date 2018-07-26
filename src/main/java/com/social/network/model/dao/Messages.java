package com.social.network.model.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Messages {
    @Id
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "conversation_id")
    private Long conversationId;
    private String text;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp;
}
