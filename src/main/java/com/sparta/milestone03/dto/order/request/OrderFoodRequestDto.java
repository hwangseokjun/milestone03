package com.sparta.milestone03.dto.order.request;

import com.sparta.milestone03.model.Food;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFoodRequestDto {
    private Long id;
    private int quantity;
    private Food food;
}
