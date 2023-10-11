package com.jirafik.order.model;


import com.jirafik.order.model.enums.OrderStatus;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "shop_id", nullable = false)
    private Long shopId;

    @Column(name = "courier_id")
    private Long courierId;

    @CreationTimestamp
    @Column(name = "generated_date")
    private Timestamp timestamp;

    @Enumerated(STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems;
}
