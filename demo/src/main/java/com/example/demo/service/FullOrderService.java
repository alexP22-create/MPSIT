package com.example.demo.service;

import com.example.demo.entity.FullOrder;
import com.example.demo.entity.ProductOrder;
import com.example.demo.entity.User;
import com.example.demo.repository.FullOrderRepository;
import com.example.demo.repository.ProductOrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FullOrderService {

    @Autowired
    private FullOrderRepository fullOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public void saveFullOrder(FullOrder fullOrder, String username) {
        User user = userRepository.findUserByUsername(username);
        fullOrder.setUser(user);
        List<ProductOrder> orders = productOrderRepository.findAllByUsername(username);
        fullOrder.setProductOrders(orders);

        fullOrderRepository.save(fullOrder);
    }

    public FullOrder getLastFullOrder(String username) {
        List<FullOrder> allOrders = fullOrderRepository.findAll();
        FullOrder last = null;
        for (FullOrder order : allOrders) {
            if (order.getUser().getUsername().equals(username)) {
                last = order;
            }
        }
        return last;
    }
}
