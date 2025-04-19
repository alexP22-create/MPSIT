package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findProductByName(@Param("name") String name);

    List<ProductOrder> findAllByUsername(String username);

}
