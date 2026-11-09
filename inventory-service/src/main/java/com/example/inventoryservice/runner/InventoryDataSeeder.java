package com.example.inventoryservice.runner;

import com.example.inventoryservice.entity.ProductStock;
import com.example.inventoryservice.repository.ProductStockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InventoryDataSeeder implements CommandLineRunner {

    private final ProductStockRepository stockRepository;

    public InventoryDataSeeder(ProductStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void run(String... args) {
        if (stockRepository.count() == 0) {
            stockRepository.save(new ProductStock(1L, "Panadol Extra", 100));
            System.out.println(">>> [Inventory-Service] Khởi tạo tồn kho mẫu cho Product ID = 1 (Tồn: 100)");
        }
    }
}