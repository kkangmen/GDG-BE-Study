package com.example.demo.order;

import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.order.dto.OrderCreateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderrepository;
    private final MemberRepository memberRepository;

    // 주문 정보 생성
    @Transactional
    public Long createOrder(@RequestBody OrderCreateRequest request){
        Member member = memberRepository.findById(request.getMemberId());

        Order order = new Order(member, request.getDateTime(), request.getSum(), request.getStatus());
        orderrepository.save(order);
        return order.getId();
    }

    // 주문 목록 조회
    @Transactional
    public List<Order> getAllOrders(){
        return orderrepository.findAll();
    }

    // 개별 주문 정보 상세 조회
    @Transactional
    public Order getOrder(Long orderId){
        Order order = orderrepository.findById(orderId);

        if (order == null){
            throw new RuntimeException("해당 주문 정보가 존재하지 않습니다.");
        }
        return order;
    }

    // 주문 취소
    @Transactional
    public void deleteOrder(Long orderId){
        orderrepository.delete(orderId);
    }
}
