package com.example.restaurantadmin.service;

import com.example.restaurantadmin.dto.ReviewDto;
import com.example.restaurantadmin.dto.TableReservationDto;
import com.example.restaurantadmin.entity.Review;
import com.example.restaurantadmin.entity.TableReservation;
import com.example.restaurantadmin.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> reviewList() {
        return reviewRepository.findAll();
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public Review getReviewById(Long id) {
        return reviewRepository.getOne(id);
    }

    public void saveReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setDate(LocalDate.now());
        review.setName(reviewDto.getName());
        review.setStars(reviewDto.getStars());
        review.setWouldRecommend(reviewDto.getWouldRecommend());
        reviewRepository.save(review);
    }
}
