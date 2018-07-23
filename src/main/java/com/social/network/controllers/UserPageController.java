package com.social.network.controllers;

import com.social.network.exceptions.UserNotExistsException;
import com.social.network.rest.BaseController;
import com.social.network.services.SocialGroupsService;
import com.social.network.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class UserPageController implements BaseController {

    private final UsersService usersService;
    private final SocialGroupsService socialGroupsService;

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("user", usersService.getUserByLogin("admin").orElseThrow(UserNotExistsException::new));
        model.addAttribute("groups", socialGroupsService.getSimpleSocialGroups());
        return "userPage";
    }
}
