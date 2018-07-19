package com.social.network.model.dao;

import com.social.network.model.enums.Roles;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @SequenceGenerator(name = "port_id_seq", sequenceName = "port_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "port_id_seq")
    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private Boolean validated;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(columnDefinition = "enum('ADMIN','USER')")
    @Enumerated(EnumType.STRING)
    private Roles role;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
