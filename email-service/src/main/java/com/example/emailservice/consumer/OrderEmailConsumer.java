package com.example.emailservice.consumer;

import com.example.emailservice.event.OrderCreatedEvent;
import com.example.emailservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEmailConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderEmailConsumer.class);
    private final EmailService emailService;

    public OrderEmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${app.kafka.topic.order}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info(">>> [Email-Service] Nhận sự kiện đơn hàng: {} từ Kafka. Bắt đầu xử lý gửi Email...", event.getOrderId());
        emailService.sendOrderInvoice(event);
    }
}