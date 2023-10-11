package com.jirafik.customer.repository;

import com.jirafik.customer.model.Order;
import com.jirafik.customer.model.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    public void save_returnSavedEntity() {

        Order order = Order.builder()
                .orderItems(List.of())
                .shopId(1L)
                .customerId(1L)
                .timestamp(Timestamp.from(Instant.now()))
                .status(OrderStatus.DELIVERY_COMPLETED)
                .build();

        Order savedOrder = orderRepository.save(order);

        assertThat(savedOrder).isNotEqualTo(null);
        assertThat(savedOrder.getId()).isGreaterThan(0);
    }

    @Test
    public void findById_returnEntity() {

        Order order = Order.builder()
                .orderItems(List.of())
                .shopId(1L)
                .customerId(1L)
                .timestamp(Timestamp.from(Instant.now()))
                .status(OrderStatus.DELIVERY_COMPLETED)
                .build();

        orderRepository.save(order);

        Long orderId = order.getId();

        Optional<Order> savedOrder = orderRepository.findById(orderId);
        log.info("Order.id = {}", orderId);

        assertThat(savedOrder.isPresent()).isEqualTo(true);
        assertThat(savedOrder.get().getStatus()).isEqualTo(OrderStatus.DELIVERY_COMPLETED);
    }


}