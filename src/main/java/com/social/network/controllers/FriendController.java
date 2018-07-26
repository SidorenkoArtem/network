package com.social.network.controllers;

import com.social.network.services.UserFriendsService;
import com.social.network.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FriendController {

    private final UserFriendsService userFriendsService;
    private final UsersService usersService;

    @RequestMapping(value = "/user/{userId}/friend", method = RequestMethod.GET)
    public String getGroups(Model model, @RequestParam(value = "userId") Long userId) {
        model.addAttribute("user", usersService.getUser(userId));
        model.addAttribute("userFriends", userFriendsService.getOtherUserFriends(userId, 0, 10));
        return "friendPage";
    }

}
