package com.social.network.rest;

import com.social.network.model.requests.MessageRequest;
import com.social.network.model.requests.UserRequest;
import com.social.network.model.responces.UsersResponse;
import com.social.network.services.MessagesService;
import com.social.network.services.UserService;
import com.social.network.services.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(tags = "User-Controller")
public class UserController {

    private final UsersService usersService;
    private final MessagesService messagesService;
    private final UserService userService;

    @PutMapping("/")
    @ApiOperation(value = "Update user")
    public ResponseEntity<Object> userUpdate(@RequestBody @Valid UserRequest userRequest) {
        userService.updateUser(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "User registration")
    public ResponseEntity<Object> userRegistration(@RequestBody @Valid UserRequest userRequest) {
        userService.userRegistration(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiOperation(value = "Get users", response = UsersResponse.class)
    public ResponseEntity<Object> getUsers(@RequestParam(value = "searchText", required = false) String searchText,
        @RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return new ResponseEntity<>(usersService.getUsers(searchText, offset, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usersService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/{userId}/messages")
    @ApiOperation(value = "Get messages by user")
    public ResponseEntity<Object> getMessages(@PathVariable(value = "userId") Long userId,
        @RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return new ResponseEntity<>(messagesService.getMessages(userId, offset, limit), HttpStatus.OK);
    }

    @GetMapping("/conversations")
    @ApiOperation(value = "Get conversation")
    public ResponseEntity<Object> getConversations(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return new ResponseEntity<>(messagesService.getConversations(offset, limit), HttpStatus.OK);
    }

    @GetMapping("/conversations/{conversationId}/messages")
    @ApiOperation(value = "Get messages by conversation id")
    public ResponseEntity<Object> getMessagesByConversationId(@PathVariable(value = "conversationId") Long conversationId,
            @RequestParam(value = "offset", defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return new ResponseEntity<>(messagesService.getMessagesByConversation(conversationId, offset, limit), HttpStatus.OK);
    }

    @PostMapping("/conversations/{conversationId}/messages")
    @ApiOperation(value = "Add messages to conversation")
    public ResponseEntity<Object> addMessagesToConversation(@PathVariable(value = "conversationId") Long conversationId,
            @RequestBody MessageRequest messageRequest) {
        messagesService.addMessagesToConversation(messageRequest, conversationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
