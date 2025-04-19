package com.example.demo.unit_tests.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductOrder;
import com.example.demo.repository.ProductOrderRepository;
import com.example.demo.service.ProductOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductOrderServiceTest {

    @Mock
    private ProductOrderRepository productOrderRepository;

    @InjectMocks
    private ProductOrderService productOrderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProductOrder_ProductFound() {
        ProductOrder order = new ProductOrder();
        String productName = "Product1";
        Product product = new Product();
        product.setName(productName);

        when(productOrderRepository.findProductByName(productName)).thenReturn(product);

        productOrderService.saveProductOrder(order, productName);

        assertEquals(product, order.getProduct());
        verify(productOrderRepository, times(1)).save(order);
    }

    @Test
    public void testSaveProductOrder_ProductNotFound() {
        ProductOrder order = new ProductOrder();
        String productName = "Product1";

        when(productOrderRepository.findProductByName(productName)).thenReturn(null);

        productOrderService.saveProductOrder(order, productName);

        assertNull(order.getProduct());
        verify(productOrderRepository, never()).save(order);
    }

    @Test
    public void testGetAllProductOrdersByUser() {
        String username = "user1";
        List<ProductOrder> orders = Arrays.asList(new ProductOrder(), new ProductOrder());

        when(productOrderRepository.findAllByUsername(username)).thenReturn(orders);

        List<ProductOrder> result = productOrderService.getAllProductOrdersByUser(username);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productOrderRepository, times(1)).findAllByUsername(username);
    }
}

