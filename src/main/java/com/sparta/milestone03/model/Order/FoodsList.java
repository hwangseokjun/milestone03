package com.sparta.milestone03.model.Order;

import com.sparta.milestone03.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class FoodsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "foodsList", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Foods> foods;

    public FoodsList(Restaurant restaurant){
        this.restaurant = restaurant;
    }

}
