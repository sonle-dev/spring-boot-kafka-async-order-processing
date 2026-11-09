package com.example.orderservice.service;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.entity.Order;
import com.example.orderservice.event.OrderCreatedEvent;
import com.example.orderservice.producer.OrderEventProducer;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public OrderService(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }

    @Transactional
    public Order createOrder(CreateOrderRequest request) {
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        double totalPrice = request.getUnitPrice() * request.getQuantity();

        // 1. Lưu đơn hàng vào cơ sở dữ liệu
        Order order = new Order(
                orderId,
                request.getProductId(),
                request.getProductName(),
                request.getQuantity(),
                totalPrice,
                request.getCustomerEmail(),
                "CREATED"
        );
        Order savedOrder = orderRepository.save(order);

        // 2. Đóng gói sự kiện và gửi bất đồng bộ lên Kafka
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getProductId(),
                savedOrder.getProductName(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice(),
                savedOrder.getCustomerEmail()
        );
        orderEventProducer.sendOrderCreatedEvent(event);

        return savedOrder;
    }
}