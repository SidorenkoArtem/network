package com.social.network.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Messages {
    @Id
    @SequenceGenerator(name = "messages_id_seq", sequenceName = "messages_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_id_seq")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "conversation_id")
    private Long conversationId;
    private String text;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
    @Column(name = "update_timestamp")
    private LocalDateTime updateTimestamp;
    private Boolean read;
}
