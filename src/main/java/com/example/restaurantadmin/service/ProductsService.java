package com.example.restaurantadmin.service;

import com.example.restaurantadmin.dto.ProductsDto;
import com.example.restaurantadmin.entity.Products;
import com.example.restaurantadmin.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    ProductsRepository productsRepository;

    public List<Products> findAllProducts() {
        return productsRepository.findAll();
    }

    public void saveProduct(ProductsDto productsDto) {
        Products currentProduct = productsRepository.getProductsById(productsDto.getId());
        currentProduct.setDescription(productsDto.getDescription());
        currentProduct.setImage(productsDto.getImage());
        currentProduct.setName(productsDto.getName());
        currentProduct.setPrice(productsDto.getPrice());
        currentProduct.setQuantity(productsDto.getQuantity());
        productsRepository.save(currentProduct);
    }

    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }

    public Products getProductById(Long id) {
        return productsRepository.getProductsById(id);
    }

    public void save(ProductsDto productsDto) {
        Products products = new Products();
        products.setDescription(productsDto.getDescription());
        products.setImage(productsDto.getImage());
        products.setName(productsDto.getName());
        products.setPrice(productsDto.getPrice());
        products.setQuantity(productsDto.getQuantity());
        products.setReducedPrice(productsDto.getReducedPrice());
        productsRepository.save(products);
    }

    public String productsNameAndPriceWithDiscount() {
        StringBuilder productsWithReducedPrice = new StringBuilder();

        String spaces = "     ";
        List<Products> productsWithDiscount = productsRepository.findProductsWithReducedPrice();
        for (Products products : productsWithDiscount) {
            String prodName = products.getName();
            String prodReducedPrice = products.getReducedPrice().toString();
            productsWithReducedPrice.append(prodName).append(spaces).append(prodReducedPrice).append(" lei <br>");
        }

        System.out.println(productsWithReducedPrice.toString());

        return productsWithReducedPrice.toString();
    }


/*    public String productsNameAndPriceWithDiscount() {
        String productsWithReducedPrice ="";

        String spaces = new String(new char[5]).replace('\0', ' ');
        List<Products> productsWithDiscount = productsRepository.findProductsWithReducedPrice();
        for (Products products : productsWithDiscount) {
            String prodName = products.getName();
            String prodReducedPrice = products.getReducedPrice().toString();
            productsWithReducedPrice = productsWithReducedPrice + prodName + spaces + prodReducedPrice + " lei <br>;
        }

        System.out.println(productsWithReducedPrice);

        return productsWithReducedPrice;
    }*/
}
