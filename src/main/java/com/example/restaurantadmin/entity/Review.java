package com.example.restaurantadmin.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "posting_date")
    private LocalDate date;

    @Column(name = "content")
    private String content;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "would_recommend")
    private Boolean wouldRecommend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Boolean getWouldRecommend() {
        return wouldRecommend;
    }

    public void setWouldRecommend(Boolean wouldRecommend) {
        this.wouldRecommend = wouldRecommend;
    }
}
