package com.jirafik.customer.service;

import com.jirafik.customer.dto.GetOrderDto;
import com.jirafik.customer.handler.ResponseHandler;
import com.jirafik.customer.model.Order;
import com.jirafik.customer.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ResponseHandler responseHandler = new ResponseHandler();

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<Object> getOrderList(Optional<Integer> page, Optional<Integer> pageSize) {

        if (page.isEmpty() || pageSize.isEmpty()) {
            page = Optional.of(0);
            pageSize = Optional.of(5);
        }

        Page<Order> orderList = orderRepository.findAll(PageRequest.of(page.get(), pageSize.get()));

        return responseHandler.generateGetOrderDtoList(HttpStatus.OK, orderList, page.get(), pageSize.get());
    }

    public ResponseEntity<Object> getOrderById(int id) {
        return responseHandler.generateOrderResponse(HttpStatus.OK, orderRepository.findById((long) id).get());
    }
}
