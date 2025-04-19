package com.example.demo.controller;

import com.example.demo.dto.ProductOrderDTO;
import com.example.demo.entity.ProductOrder;
import com.example.demo.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductOrderController {
    @Autowired
    private ProductOrderService productOrderService;

    @PostMapping(value = {"/addNewProductOrder"})
    public void addNewProductOrder(@RequestBody ProductOrderDTO dto) {

        try {
            ProductOrder order = dto.generateProduct();
            String productName = dto.getProductName();
            productOrderService.saveProductOrder(order, productName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = {"/getProductOrdersByUsername"})
    public List<ProductOrder> getProductOrdersByUsername(@RequestParam String username) {
        return productOrderService.getAllProductOrdersByUser(username);
    }

}
