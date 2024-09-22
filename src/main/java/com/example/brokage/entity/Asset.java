package com.example.brokage.entity;

import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Entity
@Table
@Data
public class Asset {
    @Id
    @SequenceGenerator(name = "ASSET_GENERATOR", allocationSize = 1, sequenceName = "SEQ_ASSET")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSET_GENERATOR")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private String assetName;
    private double size;
    private double usableSize;

}
