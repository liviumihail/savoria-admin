package com.example.restaurantadmin.repository;

import com.example.restaurantadmin.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query("SELECT products FROM Products products WHERE products.id=?1")
    Products getProductsById(Long id);

    @Query("SELECT products FROM Products products WHERE products.reducedPrice is not null")
    List<Products> findProductsWithReducedPrice();

}
