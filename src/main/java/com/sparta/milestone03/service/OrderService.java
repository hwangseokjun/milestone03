package com.sparta.milestone03.service;

import com.sparta.milestone03.dto.order.request.OrderFoodRequestDto;
import com.sparta.milestone03.dto.order.request.OrderRequestDto;
import com.sparta.milestone03.dto.order.response.OrderResponseDto;
import com.sparta.milestone03.model.Order.Foods;
import com.sparta.milestone03.model.Order.FoodsList;
import com.sparta.milestone03.model.Restaurant;
import com.sparta.milestone03.repository.FoodRepository;
import com.sparta.milestone03.repository.RestaurantRepository;
import com.sparta.milestone03.repository.order.FoodOrderRepository;
import com.sparta.milestone03.repository.order.FoodsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private FoodsListRepository foodsListRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodOrderRepository foodOrderRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    public List<OrderResponseDto> getOrder(){
        List<OrderResponseDto> responseDtos = new ArrayList<>();
        // 전체 목록 받아오기
        List<FoodsList> foodsLists = foodsListRepository.findAll();

        for ( FoodsList foodList : foodsLists ){
            responseDtos.add(new OrderResponseDto(foodList, foodList.getFoods()));
        }

        return responseDtos;
    }

    public OrderResponseDto postOrder(OrderRequestDto requestDto){

        // Id값에 맞는 레스토랑 검색 후 인스턴스로
        Restaurant restaurant = restaurantRepository
                .findById(requestDto.getRestaurantId())
                .orElseThrow( () -> new NullPointerException("음식점이 존재하지 않습니다."));

        // 검증작업1
        int totalPrice = 0;
        for ( OrderFoodRequestDto orderFoodRequestDto : requestDto.getFoods() ){
            if ( orderFoodRequestDto.getQuantity() < 1 || orderFoodRequestDto.getQuantity() > 100 ){
                throw new IllegalArgumentException("주문수량을 확인해 주세요.");
            }
            totalPrice += orderFoodRequestDto.getQuantity() * foodRepository.findById(orderFoodRequestDto.getId()).orElseThrow(()->new NullPointerException("음식이 없습니다.")).getPrice();
        }

        // 검증작업2
        if ( totalPrice < restaurant.getMinOrderPrice() ){
            throw new IllegalArgumentException("최소주문가격을 확인해 주세요.");
        }

        // 저장시작
        FoodsList foodsList = foodsListRepository.save(new FoodsList(restaurant));

        // Foods를 넣어준다.
        for ( OrderFoodRequestDto orderFoodRequestDto : requestDto.getFoods() ){
            orderFoodRequestDto.setFood(foodRepository
                    .findById(orderFoodRequestDto.getId()).get());
            foodOrderRepository.save(new Foods(foodsList, orderFoodRequestDto));
        }
        return new OrderResponseDto(
                foodsListRepository.findByRestaurant(restaurant),
                foodOrderRepository.findByFoodsList(foodsList));
    }

}
