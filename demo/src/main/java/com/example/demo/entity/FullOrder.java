package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "full_order", schema = "public")
public class FullOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    @OneToMany(mappedBy = "fullOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> productOrders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public FullOrder(OrderDetails orderDetails, List<ProductOrder> productOrders, User user) {
        this.orderDetails = orderDetails;
        this.productOrders = productOrders;
        this.user = user;
    }

    public FullOrder() {

    }
}
