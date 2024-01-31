package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.dto.AppUserDto;
import com.example.restaurantadmin.entity.AppUser;
import com.example.restaurantadmin.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    AppUserService appUserService;

    @GetMapping("users")
    private String users(Model model) {
        List<AppUser> usersList = appUserService.findAllUsers();
        model.addAttribute("users", usersList);
        return "users";
    }

    @GetMapping("addUser")
    private String addUserGet() {
        return "addUser";
    }

    @PostMapping("/addUser")
    private String addUserPost(@Valid @ModelAttribute("user") AppUserDto userDto,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "redirect:/addUser?error";
        }

        if (appUserService.findUserByEmailObject(userDto.getEmail())!=null) {
            result.rejectValue("email", null,
                    "Email deja în uz!");
            return "redirect:/addUser?error";
        }

        appUserService.saveUser(userDto);
        return "redirect:/users?success";
    }

    @GetMapping("/update-user/{id}")
    public String updateUser(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("user", appUserService.getUserById(id));
        return "update-user";
    }

    @PostMapping("/update-user/{id}")
    private String saveUser(@Valid @ModelAttribute("user") AppUserDto userDto,
                               BindingResult result) {

        if (appUserService.findUserByEmailObject(userDto.getEmail())!=null) {
            result.rejectValue("email", null,
                    "Email deja în uz!");
            return "redirect:/update-user/{id}?error";
        }

        appUserService.saveUser(userDto);
        return "redirect:/users?success";
    }

    @GetMapping(value = "/delete-user/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        appUserService.deleteUser(id);
        return "redirect:/users";
    }



}
