package com.example.orderservice.dto;

public class OrderResponse {
    private String message;
    private String orderId;
    private double totalPrice;
    private long executionTimeMs;

    public OrderResponse() {}

    public OrderResponse(String message, String orderId, double totalPrice, long executionTimeMs) {
        this.message = message;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.executionTimeMs = executionTimeMs;
    }

    public String getMessage() { return message; }
    public String getOrderId() { return orderId; }
    public double getTotalPrice() { return totalPrice; }
    public long getExecutionTimeMs() { return executionTimeMs; }
}