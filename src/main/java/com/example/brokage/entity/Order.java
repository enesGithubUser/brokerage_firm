package com.example.brokage.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
@Data
public class Order {
    @Id
    @SequenceGenerator(name = "ORDER_GENERATOR", allocationSize = 1, sequenceName = "SEQ_ORDER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_GENERATOR")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String assetName;
    private String orderSide; // BUY or SELL
    private double size;
    private double price;
    private String status; // PENDING, MATCHED, CANCELED
    private LocalDateTime createDate;

}
