package com.jirafik.order.service;

import com.jirafik.order.dto.GetOrderDto;
import com.jirafik.order.dto.GetOrderListDto;
import com.jirafik.order.dto.PostOrderDto;
import com.jirafik.order.dto.ShopItemDto;
import com.jirafik.order.model.Order;
import com.jirafik.order.model.enums.OrderStatus;
import com.jirafik.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService underTestService;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void createOrder_ReturnsGetOrderDto() {

        PostOrderDto orderDto = new PostOrderDto();
        orderDto.setShopId(1L);
        orderDto.setPrice(100);
        orderDto.setItemList(List.of(
                new ShopItemDto(13L, 3),
                new ShopItemDto(13L, 3),
                new ShopItemDto(13L, 3)
        ));

        GetOrderDto savedOrder = (GetOrderDto) underTestService.saveOrder(orderDto).getBody();

        verify(repository).save(any(Order.class));
        assertThat(savedOrder).isNotEqualTo(null);
        assertThat(savedOrder.getShopId()).isEqualTo(orderDto.getShopId());
    }

    @Test
    public void getAllOrders_ReturnsGetOrderListDto() {

        Page<Order> orders = Mockito.mock(Page.class);

        when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(orders);

        GetOrderListDto savedOrders = (GetOrderListDto) underTestService.getOrderList(1).getBody();

        assertThat(savedOrders).isNotEqualTo(null);
    }

    @Test
    void getOrderById_ReturnsGetOrderDto() {

        Order order = Order.builder()
                .orderItems(List.of())
                .shopId(1L)
                .customerId(1L)
                .timestamp(Timestamp.from(Instant.now()))
                .status(OrderStatus.DELIVERY_COMPLETED)
                .build();

        when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(order));

        GetOrderDto getOrderDto = (GetOrderDto) underTestService.getOrderById(1).getBody();

        assertThat(getOrderDto).isNotEqualTo(null);
        assertThat(getOrderDto.getShopId()).isEqualTo(order.getShopId());
    }

    @Test
    void getOrderById_ReturnErrorString() {

        int defaultOrderId = 1;

        when(repository.findById(Mockito.any(Long.class))).thenReturn(null);

        String errorResponse = (String) underTestService.getOrderById(defaultOrderId).getBody();

        assertThat(errorResponse).isEqualTo("Order not found with id = " + defaultOrderId);
    }


}