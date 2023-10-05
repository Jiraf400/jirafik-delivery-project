package com.jirafik.customer.controller;

import com.jirafik.customer.model.Order;
import com.jirafik.customer.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class CustomerController {

    private final OrderService orderService;

    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/pages/{page}/{size}")
    public ResponseEntity<Object> getOrderList(@PathVariable(required = false) Optional<Integer> page,
                                               @PathVariable(required = false) Optional<Integer> size) {

        return orderService.getOrderList(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable(required = false) int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "<h4>Hello</h4>";
    }

    //TODO create ShopItemDto with required fields;
    @PostMapping("/post")
    public Order createOrder() {
        return null;
    }

}
