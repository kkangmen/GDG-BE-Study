package com.example.demo.item.dto;

import com.example.demo.common.message.ErrorMessage;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class ItemUpdateRequest {

    private String itemName;

    @Range(min = 1000, max = 1000000, message = ErrorMessage.PRICE_RANGE)
    private Integer price;

    @Max(value = 10000, message = ErrorMessage.MAX_QUANTITY)
    private Integer quantity;
}
