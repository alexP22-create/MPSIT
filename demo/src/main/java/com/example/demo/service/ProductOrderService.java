package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductOrder;
import com.example.demo.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public void saveProductOrder(ProductOrder order, String productName) {
        Product productRef = productOrderRepository.findProductByName(productName);
        if (productRef == null)
            return;

        order.setProduct(productRef);
        productOrderRepository.save(order);
    }

    public List<ProductOrder> getAllProductOrdersByUser(String username) {
        return productOrderRepository.findAllByUsername(username);
    }

}
