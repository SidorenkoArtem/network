package com.social.network.controllers;

import com.social.network.configuration.ContextHolder;
import com.social.network.rest.BaseController;
import com.social.network.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String currentPage(final Model model) {
        model.addAttribute("otherUserPageData", userService.getCurrentUserPage());
        return "userPage";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String userPage(final Model model, @PathVariable(name = "userId") Long userId) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Boolean anonymous = authentication.getPrincipal().toString().equalsIgnoreCase("anonymousUser");
        if (!anonymous) {
            if (userId.equals(ContextHolder.userId())) {
                return "forward:/";
            }
        }
        model.addAttribute("otherUserPageData", userService.getUserPage(userId));
        return "userPage";
    }

    @RequestMapping(value = "/user/friends/{userId}", method = RequestMethod.GET)
    public String friendPage(final Model model, @PathVariable(name = "userId") Long userId) {
        model.addAttribute("userId", userId);
        return "friendsPage";
    }

    @RequestMapping(value = "/user/groups/{userId}")
    public String groupsPage(final Model model, @PathVariable(name = "userId") Long userId) {
        model.addAttribute("userId", userId);
        return "groupsPage";
    }

    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
    public String groupPage(final Model model, @PathVariable(name = "groupId") Long groupId) {
        model.addAttribute("groupData", socialGroupsService.getSocialGroup(groupId));
        return "GroupPage";
    }

    @RequestMapping(value = "/conversations", method = RequestMethod.GET)
    public String conversationPage(final Model model) {
        return "ConversationsPage";
    }

    @RequestMapping(value = "/conversation/{conversationId}", method = RequestMethod.GET)
    public String messagePage(final Model model, @PathVariable(name = "conversationId") Long conversationId) {
        model.addAttribute("conversationId", conversationId);
        return "ConversationPage";
    }
}
