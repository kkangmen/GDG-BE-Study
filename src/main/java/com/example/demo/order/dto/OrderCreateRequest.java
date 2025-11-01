package com.example.demo.order.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
//@RequiredArgsConstructor
public class OrderCreateRequest {

    private String itemName;
    private Integer quantity;

    public OrderCreateRequest(String itemName, Integer quantity){
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
