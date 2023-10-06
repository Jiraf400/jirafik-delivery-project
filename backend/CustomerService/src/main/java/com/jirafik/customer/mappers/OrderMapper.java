package com.jirafik.customer.mappers;

import com.jirafik.customer.dto.GetOrderDto;
import com.jirafik.customer.dto.PostOrderDto;
import com.jirafik.customer.model.Order;
import com.jirafik.customer.model.enums.OrderStatus;

import java.sql.Timestamp;
import java.time.Instant;

public class OrderMapper {

    public GetOrderDto mapOrderToDto(Order order) {

        return GetOrderDto.builder()
                .id(order.getId())
                .shopName("hardcoded shop name")
                .status(order.getStatus())
                .timestamp(order.getTimestamp())
                .orderItems(order.getOrderItems())
                .build();
    }

//    public Order mapDtoToOrder(PostOrderDto postOrderDto) {
//        return Order.builder()
//                .customerId(1L)
//                .status(OrderStatus.CUSTOMER_CREATED)
//                .orderItems(null)
//                .shopId((long) postOrderDto.getShopId())
//                .courierId(null)
//                .timestamp(Timestamp.from(Instant.now()))
//                .build();
//    }

}
