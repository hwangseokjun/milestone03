package com.sparta.milestone03.dto.order.request;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderFoodRequestDto> foods;
}
