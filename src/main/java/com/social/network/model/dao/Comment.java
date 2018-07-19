package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    private Long id;
    @Column(name = "post_id")
    private Long postId;
    private String text;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
