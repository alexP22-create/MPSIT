package com.example.demo.dto;

import com.example.demo.entity.FullOrder;
import com.example.demo.entity.OrderDetails;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FullOrderDTO {

    private String address;

    private String phoneNumber;

    private String username;

    private float price;

    public FullOrder generateFullOrder() {
        OrderDetails orderDetails = new OrderDetails(address, phoneNumber, LocalDateTime.now(), price);
        FullOrder order = new FullOrder();
        order.setOrderDetails(orderDetails);
        return order;
    }
}
