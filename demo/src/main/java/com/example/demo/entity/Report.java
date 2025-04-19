package com.example.demo.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Report {

    private LocalDateTime generatedDate;
    private String reportType;

    public Report(String reportType) {
        this.reportType = reportType;
        this.generatedDate = LocalDateTime.now();
    }

    public abstract String generateReportSummary();

}

