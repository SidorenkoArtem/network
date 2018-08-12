package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "wall_post")
public class WallPost {
    @Id
    @SequenceGenerator(name = "wall_id_seq", sequenceName = "wall_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wall_id_seq")
    private Long id;
    @Column(name = "wall_owner_id")
    private Long wallOwnerId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "text")
    private String text;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
