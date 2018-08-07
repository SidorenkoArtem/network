package com.social.network.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "wall_post")
public class WallPost {
    @Id
    private Long id;
    @Column(name = "wall_user_id")
    private Long wallUserId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "text")
    private String text;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
