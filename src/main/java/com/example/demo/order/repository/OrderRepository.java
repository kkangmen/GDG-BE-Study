package com.example.demo.order.repository;

import com.example.demo.order.entity.Order;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
    Order findById(Long orderId);
    void delete(Long orderId);
}
