package com.example.demo.item.dto;

import lombok.Getter;

@Getter
public class ItemUpdateRequest {
    private String itemName;
    private Integer price;
    private Integer quantity;
}
