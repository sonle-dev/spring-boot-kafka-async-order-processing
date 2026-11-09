package com.example.orderservice.controller;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> checkoutOrder(@RequestBody CreateOrderRequest request) {
        long startTime = System.currentTimeMillis();

        Order order = orderService.createOrder(request);

        long duration = System.currentTimeMillis() - startTime;
        OrderResponse response = new OrderResponse(
                "Đặt hàng thành công! Đơn hàng đang được hệ thống xử lý bất đồng bộ.",
                order.getId(),
                order.getTotalPrice(),
                duration
        );

        return ResponseEntity.ok(response);
    }
}