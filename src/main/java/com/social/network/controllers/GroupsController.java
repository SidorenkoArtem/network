package com.social.network.controllers;

import com.social.network.services.UserGroupsService;
import com.social.network.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GroupsController {

    private final UserGroupsService userGroupsService;
    private final UsersService usersService;

    @RequestMapping(value = "/user/{userId}/groups")
    public String getUserGroups(Model model, @RequestParam(value = "userId") Long userId) {
        model.addAttribute("user", usersService.getUser(userId));
        model.addAttribute("groups", userGroupsService.getOtherUserGroups(userId, 0, 10));
        return "userGroups";
    }
}
