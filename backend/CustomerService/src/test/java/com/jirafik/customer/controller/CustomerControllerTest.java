package com.jirafik.customer.controller;

import com.jirafik.customer.dto.GetOrderDto;
import com.jirafik.customer.dto.GetOrderListDto;
import com.jirafik.customer.dto.PostOrderDto;
import com.jirafik.customer.dto.ShopItemDto;
import com.jirafik.customer.model.Order;
import com.jirafik.customer.model.OrderItem;
import com.jirafik.customer.model.enums.OrderStatus;
import com.jirafik.customer.repository.OrderH2Repository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:";

    private final TestRestTemplate restTemplate;

    private final OrderH2Repository testRepository;

    public CustomerControllerTest(TestRestTemplate restTemplate, OrderH2Repository testRepository) {
        this.restTemplate = restTemplate;
        this.testRepository = testRepository;
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(port + "").concat("/api/v1/orders");
        testRepository.deleteAll();
    }


    @Test
    void shouldGetOrderList() throws InterruptedException {

        PostOrderDto orderDto = new PostOrderDto();
        orderDto.setShopId(1L);
        orderDto.setPrice(999);
        orderDto.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(1L, 3)));

        PostOrderDto orderDto2 = new PostOrderDto();
        orderDto2.setShopId(1L);
        orderDto2.setPrice(999);
        orderDto2.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(1L, 3)));


        restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);
        Thread.sleep(1000);
        restTemplate.postForObject(baseUrl + "/post", orderDto2, Order.class);


        Thread.sleep(1000);

        GetOrderListDto response = restTemplate.getForObject(baseUrl + "/pages/0/5", GetOrderListDto.class);

        assertThat(response.getDtoList().size()).isEqualTo(2);
    }

    @Test
    void shouldFindOrderById() {

        PostOrderDto orderDto = new PostOrderDto();
        orderDto.setShopId(1L);
        orderDto.setPrice(999);
        orderDto.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(2L, 3)));

        Order postOrder = restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);

        GetOrderDto response = restTemplate.getForObject(baseUrl + "/" + 1, GetOrderDto.class);

        assertThat(response.getOrderItems()).isEqualTo(postOrder.getOrderItems());

    }

    @Test
    void shouldCreateOrder() {

        PostOrderDto orderDto = new PostOrderDto();
        orderDto.setShopId(1L);
        orderDto.setPrice(999);
        orderDto.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(2L, 3)));

        Order response = restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);

        assertThat(OrderStatus.CUSTOMER_CREATED).isEqualTo(response.getStatus());
    }
}