package com.fashionshop.controller;

import com.fashionshop.domain.ItemDomain;
import com.fashionshop.entity.ItemEntity;
import com.fashionshop.entity.ItemSizeEntity;
import com.fashionshop.entity.SizeEntity;
import com.fashionshop.entity.UserEntity;
import com.fashionshop.service.ItemService;
import com.fashionshop.service.ItemSizeService;
import com.fashionshop.service.SizeService;
import com.fashionshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ItemController {
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    SizeService sizeService;
    @Autowired
    ItemSizeService itemSizeService;

    @RequestMapping("/list_product")
    public String showListProduct(Model model, Principal userPrincipal) {
        if (userPrincipal != null) {
            UserEntity user = userService.findUserByUsername(userPrincipal.getName());
            model.addAttribute("user", user);
        }
        List<ItemDomain> itemDomains = itemService.findAllItemDomain();
        model.addAttribute("itemDomains", itemDomains);
        return "product_list";
    }

    @PostMapping("/product_infor")
    public String showProductInfor(@RequestParam(name = "id") int id, Model model) {
        List<ItemSizeEntity> sizeEntities = itemSizeService.findAllItemSizeEntitiesByItem_Id(id);
        model.addAttribute("item_sizes", sizeEntities);
        return "product_infor";
    }
}
