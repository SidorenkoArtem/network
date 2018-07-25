package com.social.network.model.responces;

import com.social.network.model.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UsersResponse extends SimpleUsersResponse {
    private final Integer count;
    
    @Builder
    public UsersResponse(final List<UserDto> users, final Integer count) {
        super(users);
        this.count = count;
    }
}
