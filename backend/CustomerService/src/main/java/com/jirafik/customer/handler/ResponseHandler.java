package com.jirafik.customer.handler;

import com.jirafik.customer.dto.GetOrderDto;
import com.jirafik.customer.mappers.OrderMapper;
import com.jirafik.customer.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    private final OrderMapper orderMapper = new OrderMapper();

    public ResponseEntity<Object> generateOrderResponse(HttpStatus status, Order order) {

        GetOrderDto orderDto = orderMapper.mapOrderToDto(order);

        return new ResponseEntity<>(orderDto, status);
    }

    public ResponseEntity<Object> generateGetOrderDtoList(HttpStatus status, Page<Order> orderList, int page, int pageSize) {

        List<GetOrderDto> orderDtoList = new ArrayList<>();

        for (Order order : orderList) {
            GetOrderDto orderDto = orderMapper.mapOrderToDto(order);
            orderDtoList.add(orderDto);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("orders", orderDtoList);
        map.put("page_index", page);
        map.put("page_count", pageSize);

        return new ResponseEntity<>(map, status);
    }

}
