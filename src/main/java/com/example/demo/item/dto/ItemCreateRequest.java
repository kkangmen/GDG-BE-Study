package com.example.demo.item.dto;

import lombok.Getter;

@Getter
public class ItemCreateRequest {
    private String itemName;
    private Integer price;
    private Integer quantity;

    public ItemCreateRequest(Integer price, String itemName, Integer quantity) {
        this.price = price;
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
