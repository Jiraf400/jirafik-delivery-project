package com.jirafik.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderListDto {

    private List<GetOrderDto> dtoList;
    private int pageIndex;
    private int pageSize;
}

