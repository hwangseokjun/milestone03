package com.sparta.milestone03.repository;

import com.sparta.milestone03.model.Food;
import com.sparta.milestone03.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByRestaurant(Restaurant restaurant);
    List<Food> findByRestaurant_Id(Long restaurantId);
}
