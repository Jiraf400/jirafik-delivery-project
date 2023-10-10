package com.jirafik.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopItemDto {
    private Long shopItemId;
    private int quantity;
    private int description;
    private String image;
}
