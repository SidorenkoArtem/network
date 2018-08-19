package com.social.network.rest;

import com.social.network.model.requests.WallRequest;
import com.social.network.model.responces.WallPostResponse;
import com.social.network.model.responces.WallPostsResponse;
import com.social.network.services.WallsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Api(tags = "Wall-Controller")
@RequestMapping(value = "/wall")
@RequiredArgsConstructor
public class WallController {

    private final WallsService wallsService;

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get user post on wall", response = WallPostsResponse.class)
    public ResponseEntity<Object> getWallPosts(@PathVariable(name = "userId") Long ownerWallId,
            @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return new ResponseEntity<>(wallsService.getWallPosts(ownerWallId, page, limit), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}")
    @ApiOperation(value = "Add record to wall", response = WallPostResponse.class)
    public ResponseEntity<Object> createWallPost(@RequestBody @Valid WallRequest wallRequest,
            @PathVariable(value = "userId") Long ownerWallId) {
        return new ResponseEntity<>(wallsService.createWallPost(ownerWallId, wallRequest), HttpStatus.OK);
    }

    @DeleteMapping("/user/post/{postId}")
    @ApiOperation(value = "Delete record from wall")
    public ResponseEntity<String> deleteRecord(@PathVariable(value = "postId") Long postId) {
        wallsService.deleteWallPost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
