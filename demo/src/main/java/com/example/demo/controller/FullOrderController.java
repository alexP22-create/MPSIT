package com.example.demo.controller;

import com.example.demo.dto.FullOrderDTO;
import com.example.demo.entity.FullOrder;
import com.example.demo.service.FullOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FullOrderController {

    @Autowired
    private FullOrderService fullOrderService;

    @PostMapping(value = {"/addNewFullOrder"})
    public void addNewFullOrder(@RequestBody FullOrderDTO dto) {

        try {
            FullOrder fullOrder = dto.generateFullOrder();
            fullOrderService.saveFullOrder(fullOrder, dto.getUsername());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = {"/getLastFullOrder"})
    public FullOrder getLastFullOrder(@RequestParam String username) {
        return fullOrderService.getLastFullOrder(username);
    }

}
