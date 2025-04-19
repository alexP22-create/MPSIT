package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_order", schema = "public")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "nr")
    private int nr;

    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "full_order_id")
    private FullOrder fullOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductOrder() {
    }

    public ProductOrder(int nr) {
        this.nr = nr;
    }

    public ProductOrder(int nr, String username, Product product) {
        this.nr = nr;
        this.username = username;
        this.product = product;
    }
}
