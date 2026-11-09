package com.example.inventoryservice.consumer;

import com.example.inventoryservice.entity.ProductStock;
import com.example.inventoryservice.event.OrderCreatedEvent;
import com.example.inventoryservice.repository.ProductStockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderCreatedConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderCreatedConsumer.class);
    private final ProductStockRepository stockRepository;

    public OrderCreatedConsumer(ProductStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    @KafkaListener(topics = "${app.kafka.topic.order}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info(">>> [Inventory-Service] Nhận sự kiện đơn hàng: {} để cập nhật tồn kho.", event.getOrderId());

        ProductStock stock = stockRepository.findByProductId(event.getProductId())
                .orElse(null);

        if (stock != null) {
            int remaining = stock.getAvailableQuantity() - event.getQuantity();
            stock.setAvailableQuantity(Math.max(0, remaining));
            stockRepository.save(stock);
            log.info(">>> [Inventory-Service] Đã trừ {} sản phẩm {}. Tồn kho còn lại: {}",
                    event.getQuantity(), stock.getProductName(), stock.getAvailableQuantity());
        } else {
            log.warn(">>> [Inventory-Service] Không tìm thấy sản phẩm có ProductId: {}", event.getProductId());
        }
    }
}