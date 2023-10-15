package com.jirafik.order.controller;

import com.jirafik.order.dto.PostOrderDto;
import com.jirafik.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService customerService) {
        this.orderService = customerService;
    }

    @GetMapping({"/all/p={page}", "/all"})
    public ResponseEntity<Object> getOrderList(@PathVariable(name = "page", required = false) Optional<Integer> page) {
        if (page.isEmpty()) page = Optional.of(1);
        return orderService.getOrderList(page.get());
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
