package com.jirafik.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopItemDto {
    private Long shopItemId;
    private int quantity;
}
