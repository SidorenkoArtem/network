package com.social.network.model.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversation")
@Data
public class Conversation {
    @Id
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_id_interlocutor")
    private Long userIdInterlocutor;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp;
}
