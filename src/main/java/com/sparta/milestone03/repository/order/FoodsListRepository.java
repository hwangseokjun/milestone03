package com.sparta.milestone03.repository.order;

import com.sparta.milestone03.model.Order.FoodsList;
import com.sparta.milestone03.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodsListRepository extends JpaRepository<FoodsList, Long> {
    FoodsList findByRestaurant(Restaurant restuarant);
}
