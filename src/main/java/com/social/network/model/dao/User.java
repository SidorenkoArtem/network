package com.social.network.model.dao;

import com.social.network.model.enums.Roles;
import com.social.network.model.enums.Sex;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "email"),
            @UniqueConstraint(columnNames = "login")
        })
@Data
public class User {
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;
    @Column(unique = true)
    private String email;

    private String login;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    private String name;
    private String surname;
    private LocalDate birthday;
    @Column(columnDefinition = "enum('MALE', 'FEMALE', 'NONE')")
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(name = "photo_url")
    private String photoUrl;
    private Boolean active;
    private Boolean validated;
    @Column(columnDefinition = "enum('ADMIN', 'USER', 'NONE')")
    @Enumerated(EnumType.STRING)
    private Roles role;
    private String country;
    private String city;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PagePermission pagePermission;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "create_timestamp")
    private LocalDateTime createTimestamp;
}
