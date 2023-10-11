//package com.jirafik.order.controller;
//
//import com.jirafik.order.dto.GetOrderDto;
//import com.jirafik.order.dto.PostOrderDto;
//import com.jirafik.order.dto.ShopItemDto;
//import com.jirafik.order.model.Order;
//import com.jirafik.order.model.enums.OrderStatus;
//import com.jirafik.order.repository.OrderH2Repository;
//import com.jirafik.order.service.OrderService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//
//@Slf4j
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class CustomerControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    private OrderService customerService;
//
//    private String baseUrl = "http://localhost:";
//
//    private final TestRestTemplate restTemplate;
//
//    private final OrderH2Repository testRepository;
//
//    public CustomerControllerTest(TestRestTemplate restTemplate, OrderH2Repository testRepository) {
//        this.restTemplate = new TestRestTemplate();
//        this.testRepository = testRepository;
//    }
//
//    @BeforeEach
//    public void setUp() {
//        baseUrl = baseUrl.concat(port + "").concat("/api/v1/orders");
//        testRepository.deleteAll();
//    }
//
//
//    @Test
//    void shouldGetOrderList() throws InterruptedException {
//
//        PostOrderDto orderDto = new PostOrderDto();
//        orderDto.setShopId(1L);
//        orderDto.setPrice(999);
//        orderDto.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(1L, 3)));
//
//        PostOrderDto orderDto2 = new PostOrderDto();
//        orderDto2.setShopId(1L);
//        orderDto2.setPrice(999);
//        orderDto2.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(1L, 3)));
//
//
//        restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);
//        Thread.sleep(1000);
//        restTemplate.postForObject(baseUrl + "/post", orderDto2, Order.class);
//
//        Thread.sleep(1000);
//
//        verify(customerService, never()).getOrderList(any());
//    }
//
//    @Test
//    void shouldFindOrderById() {
//
//        PostOrderDto orderDto = new PostOrderDto();
//        orderDto.setShopId(1L);
//        orderDto.setPrice(999);
//        orderDto.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(2L, 3)));
//
//        Order postOrder = restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);
//
//        GetOrderDto response = restTemplate.getForObject(baseUrl + "/" + 1, GetOrderDto.class);
//
//        assertThat(response.getOrderItems()).isEqualTo(postOrder.getOrderItems());
//    }
//
//    @Test
//    void shouldCreateOrder() {
//
//        PostOrderDto orderDto = new PostOrderDto();
//        orderDto.setShopId(1L);
//        orderDto.setPrice(999);
//        orderDto.setItemList(List.of(new ShopItemDto(1L, 8), new ShopItemDto(2L, 3)));
//
//        Order response = restTemplate.postForObject(baseUrl + "/post", orderDto, Order.class);
//
//        assertThat(OrderStatus.CUSTOMER_CREATED).isEqualTo(response.getStatus());
//    }
//}