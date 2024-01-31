package com.example.restaurantadmin.repository;

import com.example.restaurantadmin.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
