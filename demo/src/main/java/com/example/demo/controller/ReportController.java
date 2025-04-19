package com.example.demo.controller;

import com.example.demo.dto.ReportDTO;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/generateReport")
    public ReportDTO generateReport(@RequestParam String reportType) {
        try {
            return new ReportDTO(reportService.buildReport(reportType));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
