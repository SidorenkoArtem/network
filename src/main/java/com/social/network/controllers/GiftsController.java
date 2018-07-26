package com.social.network.controllers;

import com.social.network.services.GiftsService;
import com.social.network.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GiftsController {

    private final GiftsService giftsService;
    private final UsersService usersService;

    @RequestMapping(value = "/user/{userId}/gifts")
    public String getUserGifts(final Model model, @RequestParam(value = "userId") Long userId) {
        usersService.getUser(userId);
        giftsService.getOtherUserGift(userId,0, 10);
        return "userGifts";
    }
}
