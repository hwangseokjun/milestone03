package com.sparta.milestone03.service;

import com.sparta.milestone03.dto.food.FoodRequestDto;
import com.sparta.milestone03.dto.food.FoodResponseDto;
import com.sparta.milestone03.model.Food;
import com.sparta.milestone03.model.Restaurant;
import com.sparta.milestone03.repository.FoodRepository;
import com.sparta.milestone03.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // 메뉴 검색
    public List<FoodResponseDto> getFoods(Long restaurantId){
        List<FoodResponseDto> responseDtos = new ArrayList<>();
        List<Food> foods = foodRepository.findByRestaurant_Id(restaurantId);
        for ( Food food : foods ){
            responseDtos.add(new FoodResponseDto(food));
        }
        return responseDtos;
    }

    // 메뉴 등록
    public void registerFoods(Long restaurantId, List<FoodRequestDto> requestDtos) {

        if ( !requestDtos
                .stream()
                .map(FoodRequestDto::getName)
                .allMatch(new HashSet<>()::add)) {
            throw new IllegalArgumentException("중복값은 허용되지 않습니다.");
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()->new NullPointerException("식당이 없습니다.")
                );
        if ( !isValidValue(restaurant, requestDtos).equals("pass") ){
            throw new IllegalArgumentException("입력값을 확인해주세요.");
        }

        for ( FoodRequestDto requestDto : requestDtos ){
            foodRepository.save(new Food(restaurant, requestDto));
        }
    }

    // 메뉴 검증
    // 시간이 난다면 스트림의 사용 방법에 대해서도 익숙해져 보도록 합시다.
    private String isValidValue(Restaurant restaurant, List<FoodRequestDto> requestDtos) {
        try {
            List<Food> foods = foodRepository.findByRestaurant(restaurant);
            for ( FoodRequestDto requestDto : requestDtos ){
                if ( requestDto.getPrice() < 100 || requestDto.getPrice() > 1000000) {throw new Exception("허용값을 확인해 주세요.");}
                if ( requestDto.getPrice() % 100 != 0 ) {throw new Exception("100원 단위로 입력해 주세요.");}

                for (Food food : foods){
                    if (requestDto.getName().equals(food.getName())){
                        throw new Exception("음식 이름이 중복됩니다.");
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage(); }
        return "pass";
    }
}
