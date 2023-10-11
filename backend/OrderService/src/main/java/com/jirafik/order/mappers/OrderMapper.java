package com.jirafik.order.mappers;

import com.jirafik.order.dto.GetOrderDto;
import com.jirafik.order.dto.GetOrderListDto;
import com.jirafik.order.dto.PostOrderDto;
import com.jirafik.order.dto.ShopItemDto;
import com.jirafik.order.model.Order;
import com.jirafik.order.model.OrderItem;
import com.jirafik.order.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public GetOrderDto mapOrderToGetDto(Order order) {

        return GetOrderDto.builder()
                .id(order.getId())
                .shopId(order.getShopId())
                .status(order.getStatus())
                .timestamp(order.getTimestamp())
                .orderItems(order.getOrderItems())
                .build();
    }

    public Order mapPostDtoToOrder(PostOrderDto postOrderDto) {
        return Order.builder()
                .customerId(999L)
                .courierId(999L)
                .status(OrderStatus.CUSTOMER_CREATED)
                .shopId(postOrderDto.getShopId())
                .build();
    }

    public List<OrderItem> mapPostOrderToOrderItemList(PostOrderDto postOrderDto, Order order) {

        List<OrderItem> orderItemList = new ArrayList<>();

        for (ShopItemDto itemDto : postOrderDto.getItemList()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setShopItemId(itemDto.getShopItemId());
            orderItem.setPrice(postOrderDto.getPrice());

            orderItemList.add(orderItem);
        }

        return orderItemList;
    }

    public GetOrderListDto mapOrderListToGetOrderListDto(List<Order> orderList, int page, int pageSize) {

        List<GetOrderDto> orderDtoList = new ArrayList<>();

        for (Order order : orderList) {
            GetOrderDto orderDto = mapOrderToGetDto(order);
            orderDtoList.add(orderDto);
        }

        GetOrderListDto listDto = new GetOrderListDto();
        listDto.setDtoList(orderDtoList);
        listDto.setPageIndex(page);
        listDto.setPageSize(pageSize);

        return listDto;
    }


}
