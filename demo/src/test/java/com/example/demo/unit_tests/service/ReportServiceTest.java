package com.example.demo.unit_tests.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.entity.*;
import com.example.demo.repository.FullOrderRepository;
import com.example.demo.repository.ProductOrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

public class ReportServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductOrderRepository productOrderRepository;

    @Mock
    private FullOrderRepository fullOrderRepository;

    @InjectMocks
    private ReportService reportService;

    private Product product1;
    private Product product2;
    private User user1;
    private User user2;
    private ProductOrder productOrder1;
    private ProductOrder productOrder2;
    private OrderDetails orderDetails1;
    private OrderDetails orderDetails2;
    private FullOrder fullOrder1;
    private FullOrder fullOrder2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product1 = new Product("Product1", "Description of product 1", null, 19.99f, 5, 10);
        product2 = new Product("Product2", "Description of product 2", null, 29.99f, 3, 20);
        user1 =  new User("john_doe", "password123", "john@gmail.com", "I love watches.", "Rolex", 10000.0f, new Role());
        user2 = new User("jane_smith", "securepassword", "jane@gmail.com", "Watch enthusiast.", "Omega", 15000.0f, new Role());
        productOrder1 = new ProductOrder(5, user1.getUsername(), product1);
        productOrder2 = new ProductOrder(10, user2.getUsername(), product2);
        orderDetails1 = new OrderDetails("Iuliu Maniu", "0745545686", LocalDateTime.of(2023, 1, 1, 0, 0), 50.0f);
        orderDetails2 = new OrderDetails("Dorobanti", "0745545686", LocalDateTime.of(2023, 2, 1, 0, 0), 100.0f);
        fullOrder1 = new FullOrder(orderDetails1, List.of(productOrder1), user1);
        fullOrder2 = new FullOrder(orderDetails2, List.of(productOrder2), user2);
    }

    @Test
    public void testBuildReport_Inventory() {
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        Report result = reportService.buildReport("inventory");

        assertNotNull(result);
        assertTrue(result instanceof InventoryReport);
        InventoryReport inventoryReport = (InventoryReport) result;
        assertEquals(Arrays.asList("Product1", "Product2"), inventoryReport.getProductNames());
        assertEquals(Map.of("Product1", 10, "Product2", 20), inventoryReport.getStockLevels());
    }

    @Test
    public void testBuildReport_Users() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(productOrderRepository.findAll()).thenReturn(Arrays.asList(productOrder1, productOrder2));
        when(fullOrderRepository.findAll()).thenReturn(Arrays.asList(fullOrder1, fullOrder2));

        Report result = reportService.buildReport("users");

        assertNotNull(result);
        assertTrue(result instanceof UsersReport);
        UsersReport usersReport = (UsersReport) result;
        assertEquals(Arrays.asList("john_doe", "jane_smith"), usersReport.getUserName());
        assertEquals(Map.of("john_doe", "john@gmail.com", "jane_smith", "jane@gmail.com"), usersReport.getUserEmail());
        assertEquals(Map.of("john_doe", 5, "jane_smith", 10), usersReport.getTotalOrders());
        assertEquals(Map.of("john_doe", 50.0, "jane_smith", 100.0), usersReport.getTotalSpent());
    }

    @Test
    public void testBuildReport_Invalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reportService.buildReport("invalid");
        });

        String expectedMessage = "Invalid report type: invalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

