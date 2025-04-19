package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "order_details", schema = "public")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private float price;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "delivery_days")
    private int deliveryDays;

    public OrderDetails() {
    }

    public OrderDetails(String address, String phoneNumber, LocalDateTime time) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.time = time;
    }

    public OrderDetails(String address, String phoneNumber, LocalDateTime time, float price) {
        this(address, phoneNumber, time);
        this.price = price;
    }
}
