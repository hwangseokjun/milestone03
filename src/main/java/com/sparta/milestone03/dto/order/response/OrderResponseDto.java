package com.sparta.milestone03.dto.order.response;

import com.sparta.milestone03.model.Order.Foods;
import com.sparta.milestone03.model.Order.FoodsList;
import com.sparta.milestone03.model.Restaurant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponseDto {

    private String restaurantName;
    private List<FoodsResponseDto> foods = new ArrayList<>();
    private int deliveryFee;
    private int totalPrice;

    public OrderResponseDto(FoodsList foodsList, List<Foods> foods){
        this.restaurantName = foodsList.getRestaurant().getName();
        this.deliveryFee = foodsList.getRestaurant().getDeliveryFee();
        for ( Foods food : foods ){ // 받아온 foodsList의 getFoods가 Null이다.
            this.foods.add(new FoodsResponseDto(food));
        }
        for ( FoodsResponseDto food : this.foods ){
            this.totalPrice += food.getPrice();
        }
        this.totalPrice += foodsList.getRestaurant().getDeliveryFee();
    }
}
