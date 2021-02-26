package com.dambue.spring_boot.controller;

import com.dambue.spring_boot.model.User;
import com.dambue.spring_boot.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String userInfo(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", userService.show(user.getId()));
        return "user";
    }

    @GetMapping(value = "/user/{id}")
    public String showUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.show(id));
        return "user";
    }


}
