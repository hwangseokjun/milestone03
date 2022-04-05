package com.sparta.milestone03.model.Order;

import com.sparta.milestone03.dto.order.request.OrderFoodRequestDto;
import com.sparta.milestone03.model.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Foods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="foodsList_id", nullable = false)
    private FoodsList foodsList;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

    @Column(nullable = false)
    private int quantity;

    public Foods(FoodsList foodsList, OrderFoodRequestDto orderFoodRequestDto){
        this.foodsList = foodsList;
        this.food = orderFoodRequestDto.getFood();
        this.quantity = orderFoodRequestDto.getQuantity();
    }

}