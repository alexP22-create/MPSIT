package com.example.demo.entity;

import java.util.List;
import java.util.Map;

public class UsersReport extends Report {
    private List<String> userName;
    private Map<String, String> userEmail;
    private Map<String, Integer> totalOrders;
    private Map<String, Double> totalSpent;

    public UsersReport(List<String> userNames, Map<String, String> userEmails, Map<String, Integer> totalOrders, Map<String, Double> totalSpent) {
        super("Users Report");
        this.userName = userNames;
        this.userEmail = userEmails;
        this.totalOrders = totalOrders;
        this.totalSpent = totalSpent;
    }

    public UsersReport() {
        super("Users Report");
    }

    @Override
    public String generateReportSummary() {
        StringBuilder summary = new StringBuilder();

        summary.append("Users Report Summary\n");
        summary.append("--------------------\n");

        // Total number of users
        int totalUsers = userName.size();
        summary.append("Total Users: ").append(totalUsers).append("\n");

        // List of users with their names and emails, total orders, and total spent
        summary.append("Users:\n");
        for (int i = 0; i < totalUsers; i++) {
            String name = userName.get(i);
            String email = userEmail.getOrDefault(name, "N/A");
            int orders = totalOrders.getOrDefault(name, 0);
            double spent = totalSpent.getOrDefault(name, 0.0);

            summary.append("  ").append(i + 1).append(". Name: ").append(name);
            summary.append(", Email: ").append(email);
            summary.append(", Total Orders: ").append(orders);
            summary.append(", Total Spent: $").append(spent).append("\n");
        }

        return summary.toString();
    }

    public List<String> getUserName() {
        return userName;
    }

    public Map<String, String> getUserEmail() {
        return userEmail;
    }

    public Map<String, Integer> getTotalOrders() {
        return totalOrders;
    }

    public Map<String, Double> getTotalSpent() {
        return totalSpent;
    }
}
