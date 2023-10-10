package com.jirafik.customer.service;

import com.jirafik.customer.dto.GetOrderDto;
import com.jirafik.customer.dto.GetOrderListDto;
import com.jirafik.customer.dto.PostOrderDto;
import com.jirafik.customer.exceptions.OrderNotFoundException;
import com.jirafik.customer.mappers.OrderMapper;
import com.jirafik.customer.model.*;
import com.jirafik.customer.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = new OrderMapper();

    public CustomerService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<Object> getOrderList(int page) {

        if (page > 0) page = page - 1;

        int defaultPageSize = 5;

        Page<Order> orderList = orderRepository.findAll(PageRequest.of(page, defaultPageSize));

        GetOrderListDto getOrderListDto = orderMapper.mapOrderListToGetOrderListDto(orderList, page, defaultPageSize);

        return new ResponseEntity<>(getOrderListDto, HttpStatus.OK);
    }

    public ResponseEntity<Object> getOrderById(int id) {

        Optional<Order> orderFromDb = orderRepository.findById((long) id);

        //here should be request to ShopService to find image and description of orderItem

        if (orderFromDb.isEmpty())
            throw new OrderNotFoundException("Order not found with id = " + id);

        GetOrderDto orderDto = orderMapper.mapOrderToGetDto(orderFromDb.get());

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    public ResponseEntity<Object> saveOrder(PostOrderDto postOrderDto) {

        Order order = orderMapper.mapPostDtoToOrder(postOrderDto);
        List<OrderItem> orderItemList = orderMapper.mapPostOrderToOrderItemList(postOrderDto, order);
        order.setOrderItems(orderItemList);

        orderRepository.save(order);

        GetOrderDto orderDto = orderMapper.mapOrderToGetDto(order);

        log.info("Order successfully saved");

        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }
}
