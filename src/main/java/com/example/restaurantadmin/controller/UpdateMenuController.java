package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.dto.ProductsDto;
import com.example.restaurantadmin.entity.Products;
import com.example.restaurantadmin.repository.ProductsRepository;
import com.example.restaurantadmin.service.ProductsService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Controller
public class UpdateMenuController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ProductsService productsService;

    @GetMapping("menu")
    private String showMenu(Model model) {
        List<Products> products = productsService.findAllProducts();
        model.addAttribute("products", products);
        return "menu";
    }

/*    @GetMapping("tables-advanced-menu")
    private String showAdvMenu(Model model) {
        List<Products> products = productsService.findAllProducts();
        model.addAttribute("products", products);
        return "tables-advanced-menu";
    }*/

    @PostMapping("/saveProduct")
    private String saveProduct(@Valid @ModelAttribute("product") ProductsDto productsDto,
                               BindingResult result) throws Exception  {
        productsService.saveProduct(productsDto);
        return "redirect:/menu?success";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productsService.deleteProduct(id);
        return "redirect:/menu";
    }

    @GetMapping("/update-product/{id}")
    public String updateProducts(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("product", productsService.getProductById(id));
        return "update-product";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable(name = "id") Long id, @Valid @ModelAttribute("product") ProductsDto productsDto, BindingResult result, Model model) {

        Products products = productsService.getProductById(productsDto.getId());

        if (result.hasErrors()) {
            model.addAttribute("product", productsDto);
            return "redirect:/update-product?error";
        }

        if (productsDto.getName() == null) {
            result.rejectValue("name", null,
                    "The name field is empty!");
            return "redirect:/update-product?error";
        }

        if (productsDto.getDescription() == null) {
            result.rejectValue("description", null,
                    "The description field is empty!");
            return "redirect:/update-product?error";
        }


        if (productsDto.getPrice() == null) {
            result.rejectValue("image", null,
                    "The image field is empty!");
            return "redirect:/update-product?error";
        }
        if (productsDto.getQuantity() == null) {
            result.rejectValue("price", null,
                    "The price field is empty!");
            return "redirect:/update-product?error";
        }

        productsService.saveProduct(productsDto);
        return "redirect:/menu?success=true";
    }

    @PostMapping("/addProduct")
    private String addProduct(@Valid @ModelAttribute("product") ProductsDto productsDto,
                               BindingResult result, Model model) throws Exception  {
        productsService.save(productsDto);
        if (result.hasErrors()) {
            model.addAttribute("product", productsDto);
            return "redirect:/update-product?error";
        }

        if (productsDto.getName() == null) {
            result.rejectValue("name", null,
                    "The name field is empty!");
            return "redirect:/update-product?error";
        }

        if (productsDto.getDescription() == null) {
            result.rejectValue("description", null,
                    "The description field is empty!");
            return "redirect:/update-product?error";
        }


        if (productsDto.getPrice() == null) {
            result.rejectValue("image", null,
                    "The image field is empty!");
            return "redirect:/update-product?error";
        }
        if (productsDto.getQuantity() == null) {
            result.rejectValue("price", null,
                    "The price field is empty!");
            return "redirect:/update-product?error";
        }
        productsService.save(productsDto);
        return "redirect:/menu?success";
    }

    private void saveImage(byte[] data) {
        String sql = "UPDATE products SET image = ? WHERE id = 1";
        jdbcTemplate.update(sql, data);
    }

}
