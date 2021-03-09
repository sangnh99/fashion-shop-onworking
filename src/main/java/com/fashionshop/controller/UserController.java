package com.fashionshop.controller;

import com.fashionshop.entity.UserEntity;
import com.fashionshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile")
    public String profile(Model model, Principal userPrincipal) {
        if (userPrincipal != null) {
            UserEntity user = userService.findUserByUsername(userPrincipal.getName());
            System.out.println(user.getId());
            model.addAttribute("user", user);
        }
        return "profile";
    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("user") UserEntity user) {
        userService.saveUser(user);
        return "redirect:/home";
    }
}
