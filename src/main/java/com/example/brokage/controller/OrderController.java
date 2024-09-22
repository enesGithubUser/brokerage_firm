package com.example.brokage.controller;

import com.example.brokage.entity.Order;
import com.example.brokage.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Order>> listOrders(@RequestParam Long customerId) {
        return ResponseEntity.ok(orderService.listOrders(customerId));
    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> depositMoney(@RequestParam Long customerId, @RequestParam Double amount) {
        orderService.depositMoney(customerId, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdrawMoney(@RequestParam Long customerId, @RequestParam Double amount, @RequestParam String iban) {
        orderService.withdrawMoney(customerId, amount, iban);
        return ResponseEntity.ok().build();
    }
}
