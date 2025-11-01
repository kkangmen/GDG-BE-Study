package com.example.demo.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String itemName;
    private Integer quantity;

    public Order(String itemName, Integer quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
