package com.social.network.model.dto;

import com.social.network.model.enums.Sex;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SimpleUserDto {
    private Long userId;
    private String firstName;
    private String name;
    private String photoUrl;
    private LocalDate birthday;
    private String country;
    private String city;
    private Sex gender;

    private PagePermissionDto userPermission;

    private Boolean friend;
}
