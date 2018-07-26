package com.social.network.controllers;

import com.social.network.exceptions.UserNotExistsException;
import com.social.network.rest.BaseController;
import com.social.network.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class UserPageController implements BaseController {

    private final UsersService usersService;
    private final UserGroupsService userGroupsService;
    private final GiftsService giftsService;
    private final UserFriendsService userFriendsService;

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("user", usersService.getUserByLogin("admin").orElseThrow(UserNotExistsException::new));
        model.addAttribute("groups", userGroupsService.getCurrentUserGroups(0, 10));
        model.addAttribute("gifts", giftsService.getCurrentUserGift(0, 10));
        model.addAttribute("friends", userFriendsService.getCurrentUserFriends(0, 10));
        return "userPage";
    }
}
