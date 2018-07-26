package com.social.network.model.responces;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsersResponse extends SimpleUsersResponse {
    private final Integer count;
}
