package com.jirafik.order.controller;

import com.jirafik.order.dto.PostOrderDto;
import com.jirafik.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService customerService) {
        this.orderService = customerService;
    }

    @GetMapping("/page={page}")
    public ResponseEntity<Object> getOrderList(@PathVariable(required = false) int page) {
        return orderService.getOrderList(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/post")
    public ResponseEntity<Object> createOrder(@RequestBody PostOrderDto postOrderDto) {
        return orderService.saveOrder(postOrderDto);
    }

}
