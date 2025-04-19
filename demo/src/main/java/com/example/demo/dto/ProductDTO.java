package com.example.demo.dto;

import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private String name;

    private String description;

    private String imageUrl;

    private float price;

    private int deliveryTime;

    private int nrInStock;

    public Product generateProduct() {
        Image image = new Image(imageUrl);
        return new Product(name, description, image, price, deliveryTime, nrInStock);
    }
}
