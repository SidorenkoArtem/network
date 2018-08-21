package com.social.network.model.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Gift")
@Data
public class Gift {
    @Id
    private Long id;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp = LocalDateTime.now();
}
