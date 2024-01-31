package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.entity.ShoppingCart;
import com.example.restaurantadmin.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SalesController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("sales")
    private String sales(Model model) {
        List<ShoppingCart> shoppingCartList = shoppingCartService.getSales();
        model.addAttribute("sales", shoppingCartList);
        return "sales";
    }

}
