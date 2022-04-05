package com.sparta.milestone03.service;

import com.sparta.milestone03.dto.restaurant.RestaurantRequestDto;
import com.sparta.milestone03.dto.restaurant.RestaurantResponseDto;
import com.sparta.milestone03.model.Restaurant;
import com.sparta.milestone03.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // 음식점 등록 메소드
    public RestaurantResponseDto registerRestaurant(RestaurantRequestDto requestDto) {

        if ( !isValidValue(requestDto).equals("pass") ){
            throw new IllegalArgumentException("입력값을 확인해주세요.");
        }

        Restaurant restaurant = new Restaurant(requestDto);

        return new RestaurantResponseDto(restaurantRepository.save(restaurant));
    }

    // 음식점 리스트 작성 메소드
    public List<RestaurantResponseDto> getRestaurant() {

        List<RestaurantResponseDto> responseDtos = new ArrayList<>();
        List<Restaurant> restaurants = restaurantRepository.findAll();

        for ( Restaurant restaurant : restaurants ) {
            RestaurantResponseDto responseDto = new RestaurantResponseDto(restaurant);
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }

    // 입력값 검증
    private String isValidValue(RestaurantRequestDto requestDto) {

        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        try {
            if (minOrderPrice < 1000 || minOrderPrice > 100000) {throw new Exception("허용값을 확인해 주세요.");}
            if (minOrderPrice % 100 != 0) {throw new Exception("100원 단위로 입력해 주세요.");}
            if (deliveryFee < 0 || deliveryFee > 10000) {throw new Exception("허용값을 확인해 주세요.");}
            if (deliveryFee % 500 != 0) {throw new Exception("500원 단위로 입력해 주세요.");}
        }
        catch(Exception e) {return e.getMessage();}

        return "pass";
    }
}