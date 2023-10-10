package com.jirafik.customer.service;

import com.jirafik.customer.dto.PostOrderDto;
import com.jirafik.customer.dto.ShopItemDto;
import com.jirafik.customer.exceptions.OrderNotFoundException;
import com.jirafik.customer.handler.ResponseHandler;
import com.jirafik.customer.model.*;
import com.jirafik.customer.model.enums.OrderStatus;
import com.jirafik.customer.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {
    private final OrderRepository orderRepository;
    private final ResponseHandler responseHandler = new ResponseHandler();

    public CustomerService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<Object> getOrderList(Optional<Integer> page) {

        int defaultPageSize = 5;

        Page<Order> orderList = orderRepository.findAll(PageRequest.of(page.orElse(0), defaultPageSize));

        return responseHandler.generateGetOrderDtoList(HttpStatus.OK, orderList, page.orElse(0), defaultPageSize);
    }

    public ResponseEntity<Object> getOrderById(int id) {
        Optional<Order> orderFromDb = orderRepository.findById((long) id);

        if (orderFromDb.isEmpty())
            throw new OrderNotFoundException("Order not found with id = " + id);

        return responseHandler.generateOrderResponse(HttpStatus.OK, orderFromDb.get());
    }

    public ResponseEntity<Object> saveOrder(PostOrderDto postOrderDto) {

        Order order = Order.builder()
                .customerId(999L)
                .courierId(999L)
                .status(OrderStatus.CUSTOMER_CREATED)
                .shopId(postOrderDto.getShopId())
                .build();

        List<OrderItem> orderItemList = new ArrayList<>();

        for (ShopItemDto itemDto : postOrderDto.getItemList()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setShopItemId(itemDto.getShopItemId());
            orderItem.setPrice(postOrderDto.getPrice());

            orderItemList.add(orderItem);
        }

        order.setOrderItems(orderItemList);

        try {
            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Order successfully saved");

        return responseHandler.generateOrderResponse(HttpStatus.OK, order);
    }
}
