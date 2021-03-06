package com.social.network.controllers;

import com.social.network.model.dao.TimePK;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserFriends;
import com.social.network.repositories.UserFriendsRepository;
import com.social.network.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class login {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFriendsRepository userFriendsRepository;

    @RequestMapping(value = {"/indexx"}, method = RequestMethod.GET)
    public String index(Model model) {
        final User user = userRepository.findById(0L).orElse(null);
        System.out.println(user);
        final UserFriends userFriend = userFriendsRepository.findById(new TimePK(0L, 0L)).get();
        System.out.println(userFriend);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        final Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }
}
