package com.jirafik.customer.controller;

import com.electronwill.nightconfig.core.conversion.Path;
import com.jirafik.customer.model.Order;
import com.jirafik.customer.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/orders")
public class CustomerController {

    private final OrderService orderService;

    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    //TODO add pagination
    @GetMapping("/")
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    //TODO create ShopItemDto with required fields;
    @PostMapping("/post")
    public Order getOrderById() {
        return null;
    }

}
