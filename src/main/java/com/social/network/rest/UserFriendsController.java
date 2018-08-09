package com.social.network.rest;

import com.social.network.model.enums.Status;
import com.social.network.model.requests.UserFriendsRequest;
import com.social.network.model.responces.UserResponse;
import com.social.network.services.UserFriendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userFriends")
@Api(tags = "UserFriends-Controller")
public class UserFriendsController implements BaseController {

    private final UserFriendsService userFriendsService;

    @GetMapping
    @ApiOperation(value = "Get friends of user", response = UserResponse.class)
    public ResponseEntity<Object> getUsers(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                           @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        checkStandardUserAuthenticationOptions();
        return new ResponseEntity<>(/*userFriendsService.getUserFriends(offset, limit), */HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Add request on friendship")
    public ResponseEntity<Object> addRequestOnFriendship(@Valid @RequestBody UserFriendsRequest request) {
        checkStandardUserAuthenticationOptions();
        userFriendsService.addRequestOnFriendship(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @ApiOperation(value = "Put status on request on friendship")
    public ResponseEntity<Object> putRequestOnFriendship(@RequestParam("userId") Long userId, @RequestParam("status") Status status) {
        checkStandardUserAuthenticationOptions();
        userFriendsService.changeRequestOnFriendship(userId, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete relatioship")
    public ResponseEntity<String> deleteRelationShip(@RequestParam("userId") Long userId) {
        userFriendsService.deleteRequestOnFriendship(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
