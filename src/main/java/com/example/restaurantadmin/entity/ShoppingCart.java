package com.example.restaurantadmin.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_products_id")
    private ShoppingCartProducts products;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "personal_notes")
    private String personalNotes;

    @Column(name = "count")
    private String count;

    @Column(name = "products_id")
    private String productsId;

    @Column(name = "client_email")
    private String clientEmail;

    @Column(name="date")
    private LocalDateTime dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCartProducts getProducts() {
        return products;
    }

    public void setProducts(ShoppingCartProducts products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonalNotes() {
        return personalNotes;
    }

    public void setPersonalNotes(String personalNotes) {
        this.personalNotes = personalNotes;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
