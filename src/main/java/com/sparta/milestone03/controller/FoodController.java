package com.sparta.milestone03.controller;

import com.sparta.milestone03.dto.food.FoodRequestDto;
import com.sparta.milestone03.dto.food.FoodResponseDto;
import com.sparta.milestone03.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    @Autowired
    FoodService foodService;

    // 음식점 메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> readFood(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

    // 메뉴 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId,
                             @RequestBody List<FoodRequestDto> requestDtos){
        foodService.registerFoods(restaurantId, requestDtos);
    }
}
