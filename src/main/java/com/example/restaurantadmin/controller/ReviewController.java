package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.dto.ReviewDto;
import com.example.restaurantadmin.dto.TableReservationDto;
import com.example.restaurantadmin.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    private String reviews(Model model) {
        model.addAttribute("reviews",reviewService.reviewList());
        return "reviews";
    }

    @GetMapping(value = "/deleteReview/{id}")
    public String deleteReservation(@PathVariable(name = "id") Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }

    @GetMapping("/update-review/{id}")
    public String updateReservations(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("review", reviewService.getReviewById(id));
        return "update-review";
    }

    @PostMapping("/addReview")
    private String addReservation(@Valid @ModelAttribute("review") ReviewDto reviewDto,
                                  BindingResult result, Model model) throws Exception  {

        if (result.hasErrors()) {
            model.addAttribute("review", reviewDto);
            return "redirect:/addReview?error";
        }
/*
        if (reviewDto.getEmail() == null) {
            result.rejectValue("email", null,
                    "The email field is empty!");
            return "redirect:/addReservation?error";
        }


        if (reviewDto.getReservationDateTime() == null) {
            result.rejectValue("image", null,
                    "The reservationDateTime field is empty!");
            return "redirect:/addReservation?error";
        }
        if (reviewDto.getGuestsNo() == null) {
            result.rejectValue("guestsNo", null,
                    "The guestsNo field is empty!");
            return "redirect:/addReservation?error";
        }

        if (reviewDto.getInside() == null) {
            result.rejectValue("inside", null,
                    "The inside field is empty!");
            return "redirect:/addReservation?error";
        }*/

        reviewService.saveReview(reviewDto);
        return "redirect:/reviews?success";
    }

    @GetMapping("/addReview")
    private String addReview() {
        return "addReview";
    }

}
