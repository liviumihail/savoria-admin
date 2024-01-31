package com.example.restaurantadmin.service;

import com.example.restaurantadmin.entity.ShoppingCart;
import com.example.restaurantadmin.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService {

    @Autowired
    AppUserService appUserService;

    @Autowired
    ProductsService productsService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

/*    public void saveProducts(List<CartItem> products) {
        shoppingCart.setCartItems(products);
        shoppingCartRepository.save(shoppingCart);
    }*/

/*    public List<Products> getCartItems() {
        List<Products> products = shoppingCartRepository.getProducts();
        return products;
    }*/

    public void save(ShoppingCart shoppingCart) {
        shoppingCart.setClientEmail(appUserService.getLoggedInUser());
        shoppingCart.setDateTime(LocalDateTime.now());
        shoppingCartRepository.save(shoppingCart);
    }

    public List<ShoppingCart> getSales() {
        return shoppingCartRepository.findAll();
    }

    public List<Map<String, Object>> getTotalPricesByMonth() {
        String sql = "CALL GetTotalPricesByMonth()";
        return jdbcTemplate.queryForList(sql);
    }

    public void getMonthlyTotalPrices() {
        List<Map<String, Object>> monthlyTotals = getTotalPricesByMonth();

        // Now you can iterate through the list and access the data as needed
        for (Map<String, Object> row : monthlyTotals) {
            Date monthYear = (Date) row.get("month_year");
            Double totalPrice = (Double) row.get("total_price");
        }
    }

}
