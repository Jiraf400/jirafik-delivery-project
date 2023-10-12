package com.jirafik.order.controller;

import com.jirafik.order.dto.GetOrderDto;
import com.jirafik.order.dto.GetOrderListDto;
import com.jirafik.order.dto.PostOrderDto;
import com.jirafik.order.dto.ShopItemDto;
import com.jirafik.order.model.Order;
import com.jirafik.order.repository.OrderH2Repository;
import com.jirafik.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private OrderService orderService;

    @Autowired
    private OrderH2Repository testRepository;


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

        restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);

        Thread.sleep(1000);

        GetOrderListDto response = restTemplate.getForObject(baseUrl + "/page=0", GetOrderListDto.class);

        assertThat(response).isNotEqualTo(null);
        assertThat(response.getDtoList().get(0).getShopId()).isEqualTo(orderDto.getShopId());
        assertThat(response.getPageIndex()).isEqualTo(0);
    }

    @Test
    void shouldFindOrderById() {

        PostOrderDto orderDto = new PostOrderDto();
        orderDto.setShopId(1L);
        orderDto.setPrice(999);
        orderDto.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(2L, 3)));

        Order postOrder = restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);

        GetOrderDto response = restTemplate.getForObject(baseUrl + "/" + 1, GetOrderDto.class);

        assertThat(response).isNotEqualTo(null);
        assertThat(response.getOrderItems()).isEqualTo(postOrder.getOrderItems());
    }

    @Test
    void shouldCreateOrder() {

        PostOrderDto orderDto = new PostOrderDto();
        orderDto.setShopId(1L);
        orderDto.setPrice(999);
        orderDto.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(2L, 3)));

        Order response = restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);

        assertThat(response).isNotEqualTo(null);
        assertThat(999).isEqualTo(response.getOrderItems().get(0).getPrice());
    }
}