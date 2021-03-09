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
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/manage",method = RequestMethod.GET)
    public String manage(Model model, Principal principal){
        UserEntity user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "AdminManagement";
    }

    @RequestMapping(value = "/manage/manageProduct",method = RequestMethod.GET)
    public String manageProduct(){

        return "ManageProduct";
    }
}
