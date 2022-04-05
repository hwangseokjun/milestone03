package com.sparta.milestone03.dto.food;

import com.sparta.milestone03.model.Food;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private Long id;
    private String name;
    private int price;

    public FoodResponseDto(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.price = food.getPrice();
    }
}
