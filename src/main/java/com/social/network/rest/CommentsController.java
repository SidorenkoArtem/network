package com.social.network.rest;

import com.social.network.model.requests.CommentsResponce;
import com.social.network.services.CommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
@Api(tags = "Comments-Controller")
public class CommentsController implements BaseController {

    private final CommentsService commentsService;

    @GetMapping
    @ApiOperation(value = "Get comments", response = CommentsResponce.class)
    public ResponseEntity<Object> getComments() {
        return new ResponseEntity<>(commentsService.getComments(), HttpStatus.OK);
    }
}
