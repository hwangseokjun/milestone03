package com.sparta.milestone03.repository.order;

import com.sparta.milestone03.model.Order.Foods;
import com.sparta.milestone03.model.Order.FoodsList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<Foods, Long> {
    List<Foods> findByFoodsList(FoodsList foodsList);
}
