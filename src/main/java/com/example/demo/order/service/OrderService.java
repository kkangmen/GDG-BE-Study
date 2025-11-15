package com.example.demo.order.service;

import com.example.demo.order.dto.OrderCreateRequest;
import com.example.demo.order.entity.Order;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {
    Long createOrder(@RequestBody OrderCreateRequest request);
    List<Order> getAllOrders();
    Order getOrder(Long orderId);
    void deleteOrder(Long orderId);
}
