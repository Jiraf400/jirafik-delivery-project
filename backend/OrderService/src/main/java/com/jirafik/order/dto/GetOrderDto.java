package com.jirafik.order.dto;

import com.jirafik.order.model.OrderItem;
import com.jirafik.order.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDto {
    private Long id;
    private Timestamp timestamp;
    private List<OrderItem> orderItems;
    private Long shopId;
    private OrderStatus status;
}
