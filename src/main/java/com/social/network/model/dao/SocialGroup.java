package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "social_group")
@Data
public class SocialGroup {
    @Id
    private Long id;
    private String name;
    private String header;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "image_url")
    private String imageUrl;
    private String description;
    @Column(name = "hide_readers")
    private Boolean hideReaders;
    @Column(name = "private")
    private Boolean privateGroup;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp = LocalDateTime.now();
}
