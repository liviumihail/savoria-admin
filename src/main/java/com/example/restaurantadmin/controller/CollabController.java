package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.entity.CollabForm;
import com.example.restaurantadmin.entity.Table;
import com.example.restaurantadmin.service.CollabFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CollabController {

    @Autowired
    CollabFormService collabFormService;

    @GetMapping("/collabForm")
    private String tables(Model model) {
        List<CollabForm> collabList = collabFormService.findAll();
        model.addAttribute("collabs", collabList);
        return "collabForm";
    }

}
