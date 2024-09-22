package com.example.brokage.service;
import com.example.brokage.entity.*;
import com.example.brokage.repository.AssetRepository;
import com.example.brokage.repository.CustomerRepository;
import com.example.brokage.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AssetRepository assetRepository;

    public Order createOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Asset asset = assetRepository.findByCustomerId(order.getCustomerId())
                .stream().filter(a -> a.getAssetName().equals(order.getAssetName()))
                .findFirst().orElseThrow(() -> new RuntimeException("Asset not found"));

        // Check if customer has enough TRY balance
        if ("BUY".equalsIgnoreCase(order.getOrderSide()) && customer.getBalance() < order.getSize() * order.getPrice()) {
            throw new RuntimeException("Insufficient balance");
        }

        // Update balance and asset usable size
        if ("BUY".equalsIgnoreCase(order.getOrderSide())) {
            customer.setBalance(customer.getBalance() - order.getSize() * order.getPrice());
        } else if ("SELL".equalsIgnoreCase(order.getOrderSide()) && asset.getUsableSize() < order.getSize()) {
            throw new RuntimeException("Insufficient assets");
        } else {
            asset.setUsableSize(asset.getUsableSize() - order.getSize());
        }

        customerRepository.save(customer);
        assetRepository.save(asset);
        order.setStatus("PENDING");
        order.setCreateDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> listOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Customer customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        if (!"PENDING".equals(order.getStatus())) {
            throw new RuntimeException("Only pending orders can be canceled");
        }
        order.setStatus("CANCELED");
        orderRepository.save(order);
        customer.setBalance(customer.getBalance() + order.getSize() * order.getPrice());
        customerRepository.save(customer);
    }

    public void depositMoney(Long customerId, Double amount) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setBalance(customer.getBalance() + amount);
        customerRepository.save(customer);
    }

    public void withdrawMoney(Long customerId, Double amount, String iban) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        if (customer.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        customer.setBalance(customer.getBalance() - amount);
        customerRepository.save(customer);
        // Implement IBAN transfer logic here
    }
}
