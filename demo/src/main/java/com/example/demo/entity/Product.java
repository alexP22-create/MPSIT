package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product", schema = "public")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "delivery_time")
    private int deliveryTime;

    @Column(name = "nr_in_stock")
    private int nrInStock;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image;

    public Product() {

    }

    public Product(String name, String description, Image image, float price, int deliveryTime, int nrInStock) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.nrInStock = nrInStock;
    }

}
