package com.example.orderservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Order() {}

    public Order(String id, Long productId, String productName, int quantity, double totalPrice, String customerEmail, String status) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customerEmail = customerEmail;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public String getCustomerEmail() { return customerEmail; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}