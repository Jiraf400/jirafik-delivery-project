package com.jirafik.customer.mappers;

import com.jirafik.customer.dto.GetOrderDto;
import com.jirafik.customer.dto.ShopDto;
import com.jirafik.customer.model.Order;

public class OrderMapper {

    public GetOrderDto mapOrderToDto(Order order) {

        return GetOrderDto.builder()
                .id(order.getId())
                .shopDto(new ShopDto("hardcoded shop name"))
                .status(order.getStatus())
                .timestamp(order.getTimestamp())
                .orderItems(order.getOrderItems())
                .build();
    }

}
