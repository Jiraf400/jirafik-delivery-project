package com.jirafik.customer.controller;

import com.jirafik.customer.dto.PostOrderDto;
import com.jirafik.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/page={page}")
    public ResponseEntity<Object> getOrderList(@PathVariable(required = false) Optional<Integer> page) {

        return customerService.getOrderList(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable(required = false) int id) {
        return customerService.getOrderById(id);
    }

    @PostMapping("/post")
    public ResponseEntity<Object> createOrder(@RequestBody PostOrderDto postOrderDto) {
        return customerService.saveOrder(postOrderDto);
    }

}
