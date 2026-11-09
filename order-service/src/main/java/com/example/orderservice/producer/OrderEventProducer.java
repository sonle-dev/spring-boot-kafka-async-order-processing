package com.example.orderservice.producer;

import com.example.orderservice.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderEventProducer.class);
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Value("${app.kafka.topic.order}")
    private String topicName;

    public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        // Sử dụng productId làm message key để đảm bảo trật tự đơn hàng cùng sản phẩm
        String key = String.valueOf(event.getProductId());
        log.info(">>> [Order-Service] Gửi sự kiện OrderCreatedEvent lên topic: {} [Key: {}, OrderId: {}]",
                topicName, key, event.getOrderId());
        kafkaTemplate.send(topicName, key, event);
    }
}