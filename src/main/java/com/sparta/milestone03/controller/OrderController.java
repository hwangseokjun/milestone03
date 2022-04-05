package com.sparta.milestone03.controller;

import com.sparta.milestone03.dto.order.request.OrderRequestDto;
import com.sparta.milestone03.dto.order.response.OrderResponseDto;
import com.sparta.milestone03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    // 주문 조회
    @GetMapping("/orders")
    public List<OrderResponseDto> readOrder(){
        return orderService.getOrder();
    }

    // 주문 등록
    @PostMapping("/order/request")
    public OrderResponseDto registerOrder(@RequestBody OrderRequestDto requestDto){
        return orderService.postOrder(requestDto);
    }
}
