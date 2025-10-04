package com.example.demo.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    private Long orderId;
    private String item;
    private Integer quantity;

    public Order(String item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
