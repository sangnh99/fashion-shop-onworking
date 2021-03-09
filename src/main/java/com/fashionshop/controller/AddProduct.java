package com.fashionshop.controller;

import com.fashionshop.entity.ItemAddProduct;
import com.fashionshop.service.CategoryService;
import com.fashionshop.service.ItemSizeService;
import com.fashionshop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/manage")
public class AddProduct {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemSizeService itemSizeService;

    @Autowired
    private SizeService sizeService;

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String getAddProduct(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("item_sizes", itemSizeService.findAllItemSizeEntity());
        model.addAttribute("sizes",sizeService.findAllSizeEntity());
        model.addAttribute("item",new ItemAddProduct());
        return "addProduct";
    }
}
