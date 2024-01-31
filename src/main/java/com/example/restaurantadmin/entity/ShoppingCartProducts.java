package com.example.restaurantadmin.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name ="shopping_cart_products")
@Entity
public class ShoppingCartProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "products_id")
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
