package com.social.network.rest;

import com.social.network.services.UserGroupsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groupss")
@Api(tags = "Groups-Controller")
public class GroupController {

    private final UserGroupsService userGroupsService;

    @GetMapping()
    @ApiOperation(value = "Get groups")
    public ResponseEntity<Object> getGroupsByName(@RequestParam(name = "search") String search) {

        return new ResponseEntity<>(userGroupsService.getGroups(search), HttpStatus.OK);
    }
}
