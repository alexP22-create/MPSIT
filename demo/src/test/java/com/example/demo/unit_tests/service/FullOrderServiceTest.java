package com.example.demo.unit_tests.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.example.demo.entity.FullOrder;
import com.example.demo.entity.OrderDetails;
import com.example.demo.entity.User;
import com.example.demo.entity.ProductOrder;
import com.example.demo.repository.FullOrderRepository;
import com.example.demo.repository.ProductOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FullOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FullOrderServiceTest {

    @Mock
    private FullOrderRepository fullOrderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductOrderRepository productOrderRepository;

    @InjectMocks
    private FullOrderService fullOrderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveFullOrder() {
        FullOrder fullOrder = new FullOrder();
        String username = "user1";
        User user = new User();
        user.setUsername(username);

        List<ProductOrder> productOrders = Arrays.asList(new ProductOrder(), new ProductOrder());

        when(userRepository.findUserByUsername(username)).thenReturn(user);
        when(productOrderRepository.findAllByUsername(username)).thenReturn(productOrders);

        fullOrderService.saveFullOrder(fullOrder, username);

        assertEquals(user, fullOrder.getUser());
        assertEquals(productOrders, fullOrder.getProductOrders());
        verify(fullOrderRepository, times(1)).save(fullOrder);
    }

    @Test
    public void testGetLastFullOrder() {
        User user = new User();
        user.setUsername("admin");

        FullOrder order1 = new FullOrder();
        order1.setUser(user);
        order1.setOrderDetails(new OrderDetails("Iuliu Maniu", "0745545686", LocalDateTime.of(2023, 1, 1, 0, 0), 50.4f));

        FullOrder order2 = new FullOrder();
        order2.setUser(user);
        order2.setOrderDetails(new OrderDetails("Dorobanti", "0745545686", LocalDateTime.of(2023, 2, 1, 0, 0), 34.23f));

        List<FullOrder> allOrders = Arrays.asList(order1, order2);

        when(fullOrderRepository.findAll()).thenReturn(allOrders);

        FullOrder result = fullOrderService.getLastFullOrder(user.getUsername());

        assertNotNull(result);
        assertEquals(order2, result);
    }
}

