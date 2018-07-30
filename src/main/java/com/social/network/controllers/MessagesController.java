package com.social.network.controllers;

import com.social.network.services.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MessagesController {

    private final MessagesService messagesService;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String getUserMessages(Model model, @RequestParam(value = "offset") Integer offset, @RequestParam(value = "limit") Integer limit) {
        //model.addAttribute("messages", messagesService.getUserSimpleConversation(offset, limit));
        return "messagesPage";
    }
}
