package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.dto.ProductsDto;
import com.example.restaurantadmin.dto.TableReservationDto;
import com.example.restaurantadmin.entity.Products;
import com.example.restaurantadmin.entity.TableReservation;
import com.example.restaurantadmin.service.TableReservationService;
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
public class TableReservationController {

    @Autowired
    TableReservationService tableReservationService;

    @PostMapping("/update-reservation/{id}")
    public String updateReservation(@PathVariable(name = "id") Long id, @Valid @ModelAttribute("reservation") TableReservationDto tableReservationDto, BindingResult result, Model model) {

        TableReservation tableReservation = tableReservationService.getReservationById(tableReservationDto.getId());

        if (result.hasErrors()) {
            model.addAttribute("reservation", tableReservation);
            return "redirect:/update-reservation?error";
        }

        if (tableReservationDto.getPhone() == null) {
            result.rejectValue("email", null,
                    "The phone field is empty!");
            return "redirect:/update-reservation?error";
        }

        if (tableReservationDto.getReservationDateTime() == null) {
            result.rejectValue("image", null,
                    "The date field is empty!");
            return "redirect:/update-reservation?error";
        }
        if (tableReservationDto.getGuestsNo() == null) {
            result.rejectValue("guestsNo", null,
                    "The guestsNo field is empty!");
            return "redirect:/update-reservation?error";
        }

        if (tableReservationDto.getName() == null) {
            result.rejectValue("inside", null,
                    "The inside field is empty!");
            return "redirect:/update-reservation?error";
        }

        tableReservationService.saveTableReservation(tableReservationDto);
        return "redirect:/reservations?success=true";
    }

    @GetMapping(value = "/deleteReservation/{id}")
    public String deleteReservation(@PathVariable(name = "id") Long id) {
        tableReservationService.deleteReservation(id);
        return "redirect:/reservations";
    }

    @GetMapping("/update-reservation/{id}")
    public String updateReservations(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("reservation", tableReservationService.getReservationById(id));
        return "update-reservation";
    }

    @PostMapping("/addReservation")
    private String addReservation(@Valid @ModelAttribute("reservation") TableReservationDto tableReservationDto,
                              BindingResult result, Model model) throws Exception  {

        if (result.hasErrors()) {
            model.addAttribute("reservation", tableReservationDto);
            return "redirect:/addReservation?error";
        }

        if (tableReservationDto.getPhone() == null) {
            result.rejectValue("email", null,
                    "The email field is empty!");
            return "redirect:/addReservation?error";
        }


        if (tableReservationDto.getReservationDateTime() == null) {
            result.rejectValue("image", null,
                    "The reservationDateTime field is empty!");
            return "redirect:/addReservation?error";
        }
        if (tableReservationDto.getGuestsNo() == null) {
            result.rejectValue("guestsNo", null,
                    "The guestsNo field is empty!");
            return "redirect:/addReservation?error";
        }

        if (tableReservationDto.getName() == null) {
            result.rejectValue("inside", null,
                    "The inside field is empty!");
            return "redirect:/addReservation?error";
        }

        tableReservationService.saveTableReservation(tableReservationDto);
        return "redirect:/reservations?success";
    }

    @GetMapping("/addReservation")
    private String addReservation() {
        return "addReservation";
    }

    @GetMapping("/reservations")
    public String reservations(Model model) {
        List<TableReservation> reservations = tableReservationService.findAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservations";
    }
}
