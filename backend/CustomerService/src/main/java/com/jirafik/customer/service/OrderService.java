package com.jirafik.customer.service;

import com.jirafik.customer.model.Order;
import com.jirafik.customer.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    //TODO custom exception handling
    public Order getOrderById(int id) {
        return orderRepository.findById((long) id).orElseThrow(() -> new RuntimeException("something went wrong"));
    }
}
