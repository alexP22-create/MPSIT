package com.example.demo.dto;

import com.example.demo.entity.Report;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDTO {
    private String reportType;
    private LocalDateTime generatedDate;
    private String reportSummary;

    // Constructors
    public ReportDTO() {
    }

    public ReportDTO(Report report) {
        this.reportType = report.getReportType();
        this.generatedDate = report.getGeneratedDate();
        this.reportSummary = report.generateReportSummary();
    }

}
