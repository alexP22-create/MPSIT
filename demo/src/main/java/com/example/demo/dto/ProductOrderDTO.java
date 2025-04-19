package com.example.demo.dto;

import com.example.demo.entity.ProductOrder;
import lombok.Data;

@Data
public class ProductOrderDTO {

    private String productName;

    private int nr;

    private String username;

    public ProductOrder generateProduct() {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setUsername(username);
        productOrder.setNr(nr);
        return productOrder;
    }

}
