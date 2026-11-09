package com.example.orderservice.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderCreatedEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;
    private Long productId;
    private String productName;
    private int quantity;
    private double totalPrice;
    private String customerEmail;
    private String timestamp;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(String orderId, Long productId, String productName, int quantity, double totalPrice, String customerEmail) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customerEmail = customerEmail;
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}