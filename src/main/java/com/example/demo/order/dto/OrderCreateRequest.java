package com.example.demo.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
//@RequiredArgsConstructor
public class OrderCreateRequest {

    private Long memberId;
    private String status;
    private Integer sum;
    private LocalDateTime dateTime;

    public OrderCreateRequest(String status, Integer sum, LocalDateTime dateTime){
        this.status = status;
        this.sum = sum;
        this.dateTime = dateTime;
    }
}
