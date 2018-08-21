package com.social.network.model.dto;

import lombok.Data;

@Data
public class PagePermissionDto {
    private Boolean showWall;
    private Boolean showLocation;
    private Boolean showGifts;
    private Boolean showGroups;
    private Boolean showFriends;
    private Boolean showGender;
    private Boolean showBirthday;

}
