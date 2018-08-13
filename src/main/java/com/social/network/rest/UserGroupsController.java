package com.social.network.rest;

import com.social.network.model.dao.UserGroup;
import com.social.network.model.enums.Status;
import com.social.network.model.responces.UserGroupsResponse;
import com.social.network.services.UserGroupsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "UserGroups-Controller")
@RequestMapping(value = "/user/groups")
@RequiredArgsConstructor
public class UserGroupsController {

//    private final UserGroupsService userGroupsService;


//    @PostMapping("/{groupId}")
//    @ApiOperation(value = "Create request to group")
//    public ResponseEntity<String> createRequestToGroup(@RequestParam(value = "groupId") Long groupId) {
//        userGroupsService.createRequestToGroup(groupId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PutMapping("/{groupId}")
//    @ApiOperation(value = "Process user request to group")
//    public ResponseEntity<Object> processUserToGroup(@RequestParam(value = "groupId") Long groupId,
//            @Valid @RequestBody UserGroup request) {
//        userGroupsService.processUserToGroup(groupId, request);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{groupId}")
//    @ApiOperation(value = "Unsubscribe from goup")
//    public ResponseEntity<String> delete(@RequestParam(value = "groupId") Long groupId) {
//        userGroupsService.removeFromUserGroup(groupId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
