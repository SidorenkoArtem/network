package com.social.network.model.dao;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "page_permission")
public class PagePermission {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "show_groups")
    private Boolean showGroups;
    @Column(name = "show_wall")
    private Boolean showWall;
    @Column(name = "show_location")
    private Boolean showLocation;
    @Column(name = "show_gift")
    private Boolean showGifts;
    @Column(name = "show_friends")
    private Boolean showFriends;
}