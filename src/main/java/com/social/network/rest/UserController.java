package com.social.network.rest;

import com.social.network.model.responces.SocialGroupResponse;
import com.social.network.model.responces.UsersResponse;
import com.social.network.services.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Api(tags = "User-Controller")
public class UserController {

    private final UsersService usersService;

    @GetMapping("/")
    @ApiOperation(value = "Get users", response = UsersResponse.class)
    public ResponseEntity<Object> getUsers(@RequestParam(value = "searchText", required = false) String searchText) {
        return new ResponseEntity<>(usersService.getUsers(searchText), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usersService.getUser(id), HttpStatus.OK);
    }


    @GetMapping("{userId}/socialGroups")//TODO
    @ApiOperation(value = "Get user's social groups", response = SocialGroupResponse.class)
    public ResponseEntity<Object> getSocialGroups() {
        return new ResponseEntity<>(usersService.getUserSocialGroups(), HttpStatus.OK);
    }
}
