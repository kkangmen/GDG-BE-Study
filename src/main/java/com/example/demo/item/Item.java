package com.example.demo.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity // JPA는 이 클래스의 객체들을 영속성 컨텍스트로 관리하겠다.(Item 테이블로 인식)
@Table(name = "items")
@NoArgsConstructor
public class Item {

    // Item 테이블의 PK를 의미한다.
    @Id
    // PK값은 자동으로 생성된다. 개발자가 객체를 persist하려고 할 때, ID값을 비워두고 INSERT 쿼리를 보낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private Integer price;

    @Column(name = "item_quantity")
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
