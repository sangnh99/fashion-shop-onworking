package com.fashionshop.controller;

import com.fashionshop.entity.UserEntity;
import com.fashionshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage(Principal principal) {
        boolean checkAdmin = false;
        if (principal != null) {
            UserEntity user = userService.findUserByUsername(principal.getName());
            if ("ROLE_ADMIN".equals(user.getRole().getRoleName())) {
                checkAdmin = true;
                return "redirect:/admin";
            }
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeForUser(Model model, Principal principal) {
        if (principal != null) {
            UserEntity user = userService.findUserByUsername(principal.getName());
            model.addAttribute("user", user);
        }
        return "home";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String homeAdmin(Model model, Principal principal) {
        UserEntity user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "homeAdmin";
    }

}
