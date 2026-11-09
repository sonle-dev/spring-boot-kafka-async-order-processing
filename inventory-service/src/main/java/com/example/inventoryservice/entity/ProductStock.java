package com.example.inventoryservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_stocks")
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", unique = true, nullable = false)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "available_quantity")
    private int availableQuantity;

    public ProductStock() {}

    public ProductStock(Long productId, String productName, int availableQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.availableQuantity = availableQuantity;
    }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
}