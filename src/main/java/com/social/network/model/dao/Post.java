package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {
    @Id
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "group_id")
    private Long groupId;
    private String text;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
