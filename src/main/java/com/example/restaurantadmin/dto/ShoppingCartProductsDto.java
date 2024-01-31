package com.example.restaurantadmin.dto;

public class ShoppingCartProductsDto {

    private Long id;

    private Long productsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }
}
