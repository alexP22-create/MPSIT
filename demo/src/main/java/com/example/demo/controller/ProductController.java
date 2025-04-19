package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = {"/addNewProduct"})
    public void addNewProduct(@RequestBody ProductDTO dto) {

        try {
            Product product = dto.generateProduct();
            productService.saveProduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @PutMapping("/updateProduct")
    public void updateProduct(@RequestBody ProductDTO dto) {
        try {
            Product updatedProduct = dto.generateProduct();
            productService.updateProduct(updatedProduct);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping({"/getProduct"})
    public Product getProduct(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    @DeleteMapping({"/deleteProduct"})
    public void deleteProduct(@RequestParam String name) {
        productService.deleteProduct(name);
    }

}

