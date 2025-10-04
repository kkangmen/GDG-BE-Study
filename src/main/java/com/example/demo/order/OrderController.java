package com.example.demo.order;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderservice;

    // 주문 정보 생성
    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderCreateRequest request){
        Long orderId = orderservice.createOrder(request);
        return ResponseEntity.created(URI.create("/orders" + orderId)).build();
    }

    // 주문 목록 조회
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orderList =  orderservice.getAllOrders();
        return ResponseEntity.ok(orderList);
    }

    // 개별 주문 정보 상세 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
        Order order = orderservice.getOrder(orderId);
        return ResponseEntity.ok(order);
    }

    // 주문 취소
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
        orderservice.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
