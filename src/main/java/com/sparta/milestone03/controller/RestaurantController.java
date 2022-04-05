package com.sparta.milestone03.controller;

import com.sparta.milestone03.dto.restaurant.RestaurantRequestDto;
import com.sparta.milestone03.dto.restaurant.RestaurantResponseDto;
import com.sparta.milestone03.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> readRestaurant(){
        return restaurantService.getRestaurant();
    }

    @PostMapping("/restaurant/register")
    public RestaurantResponseDto registerRestaurant(@RequestBody RestaurantRequestDto requestDto){
        return restaurantService.registerRestaurant(requestDto);
    }

}
