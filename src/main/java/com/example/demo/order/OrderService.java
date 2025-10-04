package com.example.demo.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderrepository;

    // 주문 정보 생성
    public Long createOrder(@RequestBody OrderCreateRequest request){
        Order order = new Order(request.getItem(), request.getQuantity());
        orderrepository.save(order);
        return order.getOrderId();
    }

    // 주문 목록 조회
    public List<Order> getAllOrders(){
        return orderrepository.findAll();
    }

    // 개별 주문 정보 상세 조회
    public Order getOrder(Long orderId){
        return orderrepository.findById(orderId);
    }

    // 주문 취소
    public void deleteOrder(Long orderId){
        orderrepository.delete(orderId);
    }
}
