package com.example.brokage.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Customer {
    @Id
    @SequenceGenerator(name = "CUSTOMER_GENERATOR", allocationSize = 1, sequenceName = "SEQ_CUSTOMER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_GENERATOR")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Double balance; // TRY balance
}
