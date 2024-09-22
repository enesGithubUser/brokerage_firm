package com.example.brokage.repository;

import com.example.brokage.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByCustomerId(Long customerId);
    Asset findByCustomerIdAndAssetName(Long customerId, String assetName);
}
