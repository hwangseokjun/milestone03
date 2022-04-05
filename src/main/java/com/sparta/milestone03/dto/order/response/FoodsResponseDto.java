package com.sparta.milestone03.dto.order.response;

import com.sparta.milestone03.model.Order.Foods;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoodsResponseDto {
    private String name;
    private int quantity;
    private int price;

    public FoodsResponseDto(Foods foods){
        this.name = foods.getFood().getName();
        this.quantity = foods.getQuantity();
        this.price = foods.getFood().getPrice() * foods.getQuantity();
    }
}
