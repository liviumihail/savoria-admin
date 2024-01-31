package com.example.restaurantadmin.repository;

import com.example.restaurantadmin.entity.ShoppingCartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShoppingCartProductsRepository extends JpaRepository<ShoppingCartProducts, Long>{

    @Query("SELECT shoppingCartProducts.productsId FROM ShoppingCartProducts shoppingCartProducts")
    List<Long> getProductsIds();

    @Modifying
    @Transactional
    @Query(value = "DELETE from shopping_cart_products where products_id=?1 LIMIT 1", nativeQuery = true)
    void deleteByProductId(Long id);

    @Query(value = "SELECT GROUP_CONCAT(products_id SEPARATOR ', ') as products FROM shopping_cart_products", nativeQuery = true)
    String getProductsIdsAsString();

    @Query("SELECT COUNT(products) FROM ShoppingCartProducts products")
    Long countProducts();

}
