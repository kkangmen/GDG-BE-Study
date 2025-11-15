package com.example.demo.order.entity;

import com.example.demo.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "order_date")
    private LocalDateTime dateTime;

    @Column(name = "total_sum")
    private int sum;

    @Column(name = "order_status", length = 20)
    private String status;

    public Order(Member member, LocalDateTime dateTime, int sum, String status) {
        this.member = member;
        this.dateTime = dateTime;
        this.sum = sum;
        this.status = status;
    }
}
