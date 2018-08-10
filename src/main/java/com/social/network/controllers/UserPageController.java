package com.social.network.controllers;

import com.social.network.rest.BaseController;
import com.social.network.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserPageController implements BaseController {

    private final UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String currentPage(final Model model) {
        model.addAttribute("otherUserPageData", userService.getCurrentUserPage());
        return "userPage";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String userPage(final Model model, @PathVariable(name = "userId") Long userId) {
        model.addAttribute("otherUserPageData", userService.getUserPage(userId));
        return "userPage";
    }

    @RequestMapping(value = "/user/friends/{userId}", method = RequestMethod.GET)
    public String friendPage(final Model model, @PathVariable(name = "userId") Long userId) {
        model.addAttribute("userId", userId);
        return "friendsPage";
    }

    @RequestMapping(value = "/user/groups/{userId}")
    public String groupPage(final Model model, @PathVariable(name = "userId") Long userId) {
        model.addAttribute("userId", userId);
        return "groupsPage";
    }
}
