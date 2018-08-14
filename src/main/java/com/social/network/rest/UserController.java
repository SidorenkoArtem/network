package com.social.network.rest;

import com.social.network.configuration.ContextHolder;
import com.social.network.model.enums.Status;
import com.social.network.model.requests.MessageRequest;
import com.social.network.model.requests.UserRequest;
import com.social.network.model.responces.SimpleSocialGroupsResponse;
import com.social.network.model.responces.UserFriendsResponse;
import com.social.network.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.ContentHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(tags = "User-Controller")
public class UserController {

    private static String UPLOADED_FOLDER = "I://";

    private final UsersService usersService;
    private final MessagesService messagesService;
    private final UserService userService;
    private final UserFriendsService userFriendsService;
    private final UserGroupsService userGroupsService;

    @PutMapping()
    @ApiOperation(value = "Update user")
    public ResponseEntity<Object> userUpdate(@RequestBody @Valid UserRequest userRequest) {
        userService.updateUser(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "User registration")
    public ResponseEntity<String> userRegistration(@RequestBody @Valid UserRequest userRequest) {
        userService.userRegistration(userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usersService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/messages")
    @ApiOperation(value = "Send message")
    public ResponseEntity<Object> sendMessage(@RequestBody MessageRequest messageRequest) {
        messagesService.sendMessage(messageRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/conversations")
    @ApiOperation(value = "Get conversation")
    public ResponseEntity<Object> getConversations(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return new ResponseEntity<>(messagesService.getConversations(page, limit), HttpStatus.OK);
    }

//    @GetMapping("/{userId}/messages")
//    @ApiOperation(value = "Get messages by user")
//    public ResponseEntity<Object> getMessages(@PathVariable(value = "userId") Long userId,
//            @RequestParam(value = "page", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
//        return new ResponseEntity<>(messagesService.getMessages(userId, offset, limit), HttpStatus.OK);
//    }

    @GetMapping("/{userId}/friends")
    @ApiOperation(value = "Get user friends", response = UserFriendsResponse.class)
    public ResponseEntity<Object> getUserFriend(@PathVariable(value = "userId") Long userId, @RequestParam(name = "status", defaultValue = "APPROVED")Status status,
                                        @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "limit", defaultValue = "5") Integer limit) {
        return new ResponseEntity<>(userFriendsService.getFriends(userId, status, page, limit), HttpStatus.OK);
    }

    @PostMapping("/{userId}/friends")
    @ApiOperation(value = "Create request on friendship")
    public ResponseEntity<String> requestOnFriendship(@PathVariable(value = "userId") Long userId) {
        userFriendsService.createRequestOnFriendship(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}/friends")
    @ApiOperation(value = "")
    public ResponseEntity<String> processRequestOnFriendship(@PathVariable(value = "userId") Long userId, @RequestBody Status status) {
        userFriendsService.changeRequestOnFriendship(userId, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}/friends")
    @ApiOperation(value = "Deleting user from friends")
    public ResponseEntity<String> deletingFromFriends(@PathVariable(name = "userId") Long userId) {
        userFriendsService.deleteFriend(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/groups")
    @ApiOperation(value = "Get user groups", response = SimpleSocialGroupsResponse.class)
    public ResponseEntity<Object> getUserGroups(@PathVariable(name = "userId") Long userId,
            @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "limit", defaultValue = "5") Integer limit) {
        return new ResponseEntity<>(userGroupsService.getOtherUserGroups(userId, page, limit), HttpStatus.OK);
    }

    @PostMapping("/groups/{groupId}")
    @ApiOperation(value = "Sign to group")
    public ResponseEntity<String> signToGroup(@PathVariable(value = "groupId") Long groupId) {
        userGroupsService.createRequestToGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/groups/{groupId}")
    @ApiOperation(value = "Delete from group")
    public ResponseEntity<String> deleteUserGroup(@PathVariable(value = "groupId") Long groupId) {
        userGroupsService.deleteSocialGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/image")
    @ApiOperation(value = "Upload file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please, load file", HttpStatus.OK);
        }
        try {
            saveUploadFile(Arrays.asList(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.getOriginalFilename());
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    public void saveUploadFile(final List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }

    }

}