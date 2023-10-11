package com.jirafik.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderDto {
    private Long shopId;
    private int price;
    private List<ShopItemDto> itemList;
}
