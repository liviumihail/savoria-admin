package com.example.restaurantadmin.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long quantity;

    @Lob
    private byte[] image;

    private Double reducedPrice;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(Double reducedPrice) {
        this.reducedPrice = reducedPrice;
    }
}
