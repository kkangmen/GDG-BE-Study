package com.example.demo.item.dto;

import com.example.demo.common.message.ErrorMessage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class ItemCreateRequest {

    @NotNull(message = ErrorMessage.ITEM_NAME_NOT_NULL)
    private String itemName;

    @NotNull(message = ErrorMessage.PRICE_NOT_NULL)
    @Range(min = 1000, max = 1000000, message = ErrorMessage.PRICE_RANGE)
    private Integer price;

    @NotNull(message = "수량은 비어있을 수 없습니다.")
    @Max(value = 10000, message = ErrorMessage.MAX_QUANTITY)
    private Integer quantity;

    public ItemCreateRequest(Integer price, String itemName, Integer quantity) {
        this.price = price;
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
