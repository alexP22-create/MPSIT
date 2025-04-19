package com.example.demo.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

public class InventoryReport extends Report {
    private List<String> productNames;
    private Map<String, Integer> stockLevels;

    public InventoryReport(List<String> productNames, Map<String, Integer> stockLevels) {
        super("Inventory Report");
        this.productNames = productNames;
        this.stockLevels = stockLevels;
    }

    public InventoryReport() {
        super("Inventory Report");
    }

    @Override
    public String generateReportSummary() {
        StringBuilder summary = new StringBuilder();

        summary.append("Inventory Report Summary\n");
        summary.append("------------------------\n");

        // Total number of products
        int totalProducts = productNames.size();
        summary.append("Total Products: ").append(totalProducts).append("\n");

        // List of products with their names and stock levels
        summary.append("Products:\n");
        for (int i = 0; i < totalProducts; i++) {
            String name = productNames.get(i);
            int stock = stockLevels.getOrDefault(name, 0);

            summary.append("  ").append(i + 1).append(". Product: ").append(name);
            summary.append(", Stock Level: ").append(stock).append("\n");
        }

        return summary.toString();
    }

    public List<String> getProductNames() {
        return productNames;
    }

    public Map<String, Integer> getStockLevels() {
        return stockLevels;
    }

}
