package com.jirafik.order.repository;

import com.jirafik.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderH2Repository extends JpaRepository<Order, Long> {
}
