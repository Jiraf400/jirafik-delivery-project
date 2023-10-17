package com.jirafik.order.service;

import com.jirafik.order.dto.GetOrderDto;
import com.jirafik.order.dto.GetOrderListDto;
import com.jirafik.order.dto.PostOrderDto;
import com.jirafik.order.mappers.OrderMapper;
import com.jirafik.order.model.*;
import com.jirafik.order.repository.OrderRepository;
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
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = new OrderMapper();

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<Object> getOrderList(int page) {
        if(page < 1)
            return new ResponseEntity<>("Page index cannot be less than 1", HttpStatus.BAD_REQUEST);

        int defaultPageSize = 5;

        Page<Order> orderPage = orderRepository.findAll(PageRequest.of(page - 1, defaultPageSize));

        List<Order> orderList = orderPage.getContent();

        GetOrderListDto getOrderListDto = orderMapper.mapOrderListToGetOrderListDto(orderList, page, defaultPageSize);

        return new ResponseEntity<>(getOrderListDto, HttpStatus.OK);
    }

    public ResponseEntity<Object> getOrderById(int id) {

        Optional<Order> orderFromDb = orderRepository.findById((long) id);

        if (orderFromDb.isEmpty()) {
            log.error("Order not found with id = " + id);
            return new ResponseEntity<>("Order not found with id = " + id, HttpStatus.NOT_FOUND);
        }

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
