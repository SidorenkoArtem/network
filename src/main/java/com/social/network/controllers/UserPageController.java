package com.social.network.controllers;

import com.social.network.rest.BaseController;
import com.social.network.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class UserPageController implements BaseController {

    private final UserService userService;
    private final SocialGroupsService socialGroupsService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String userPage(final Model model, @PathVariable(name = "userId") Long userId) {
        model.addAttribute("otherUserPageData", userService.getUserPage(userId));
        return "userPage";
    }

    @RequestMapping(value = "/groups/{groupId}", method = RequestMethod.GET)
    public String groupPage(final Model model, @PathVariable(name = "groupId") Long groupId) {
        model.addAttribute("groupData", socialGroupsService.getSocialGroup(groupId));
        return "GroupPage";
    }

    @RequestMapping(value = "/userFriendPage/{userId}", method = RequestMethod.GET)
    public String friendPage(final Model model, @PathVariable(name = "userId") Long userId) {
        model.addAttribute("userId", userId);
        return "friendsPage";
    }
}
