package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.dto.ProductsDto;
import com.example.restaurantadmin.dto.TableDto;
import com.example.restaurantadmin.entity.Table;
import com.example.restaurantadmin.service.TableService;
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
public class TableController {

    @Autowired
    TableService tableService;

    @GetMapping("/tables")
    private String tables(Model model) {
        List<Table> tablesList = tableService.findAllTables();
        model.addAttribute("tables", tablesList);
        return "tables";
    }

    @GetMapping("addTable")
    private String addTable() {
        return "addTable";
    }

    @PostMapping("/addTable")
    private String addTable(@Valid @ModelAttribute("table") TableDto table,
                              BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("product", table);
            return "redirect:/addTable?error";
        }

        if (tableService.existsTableWithSameNumber(table.getTableNo())!=null) {
            result.rejectValue("tableNo", null,
                    "Masa cu numărul " + table.getTableNo() + "există deja!");
            return "redirect:/addTable?error";
        }

        tableService.saveTable(table);
        return "redirect:/tables?success";
    }

    @PostMapping("/update-table/{id}")
    private String saveProduct(@Valid @ModelAttribute("table") TableDto tableDto,
                               BindingResult result) throws Exception  {
        if (tableService.findTableByNumber(tableDto.getTableNo())!=null) {
            result.rejectValue("tableNo", null,
                    "Masa cu numărul " + tableDto.getTableNo() + "există deja!");
            return "redirect:/update-table/{id}?error";
        }

        tableService.saveTable(tableDto);
        return "redirect:/tables?success";
    }

    @GetMapping(value = "/delete-table/{id}")
    public String deleteTable(@PathVariable(name = "id") Long id) {
        tableService.deleteTable(id);
        return "redirect:/tables";
    }

    @GetMapping("/update-table/{id}")
    public String updateProducts(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("table", tableService.getTableById(id));
        return "update-table";
    }
}
