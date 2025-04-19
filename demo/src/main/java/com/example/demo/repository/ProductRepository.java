package com.example.demo.repository;

import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findProductByName(@Param("name") String name);

    @Modifying
    @Query("DELETE FROM Product p WHERE p.name = :name")
    void deleteProductByName(String name);

    @Query("SELECT i FROM Image i WHERE i.url = :url")
    Image findImageByUrl(String url);

    boolean existsByName(String name);
}
