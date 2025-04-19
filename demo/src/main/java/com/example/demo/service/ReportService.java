package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.FullOrderRepository;
import com.example.demo.repository.ProductOrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private FullOrderRepository fullOrderRepository;

    public Report buildReport(String reportType) {
        switch (reportType.toLowerCase()) {
            case "inventory":
                return buildInventoryReport();
            case "users":
                return buildUsersReport();
            default:
                throw new IllegalArgumentException("Invalid report type: " + reportType);
        }
    }

    private InventoryReport buildInventoryReport() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new InventoryReport();
        }

        List<String> productNames = productRepository.findAll()
                            .parallelStream()
                            .map(Product::getName)
                            .toList();
        Map<String, Integer> productStockLevels = productRepository.findAll()
                .parallelStream()
                .collect(Collectors.toMap(Product::getName, Product::getNrInStock));

        return new InventoryReport(productNames, productStockLevels);
    }

    private UsersReport buildUsersReport() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new UsersReport();
        }

        List<String> userNames = userRepository.findAll()
                            .parallelStream()
                            .map(User::getUsername)
                            .toList();

        Map<String, String> userEmails = userRepository.findAll()
                .parallelStream()
                .collect(Collectors.toMap(User::getUsername, User::getEmail));

        Map<String, Integer> totalOrders = productOrderRepository.findAll()
                .parallelStream()
                .collect(Collectors.groupingBy(ProductOrder::getUsername, Collectors.summingInt(ProductOrder::getNr)));

        Map<String, Double> totalSpend = fullOrderRepository.findAll()
                .parallelStream()
                .collect(Collectors.groupingBy(order -> order.getUser().getUsername(), Collectors.summingDouble(fullOrder -> fullOrder.getOrderDetails().getPrice())));

        return new UsersReport(userNames, userEmails, totalOrders, totalSpend);
    }

}
