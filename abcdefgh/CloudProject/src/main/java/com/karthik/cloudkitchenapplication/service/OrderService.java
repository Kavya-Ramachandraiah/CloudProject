package com.karthik.cloudkitchenapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.cloudkitchenapplication.entity.Order;
import com.karthik.cloudkitchenapplication.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        // Save the order and return the saved order
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long orderId) {
        // Retrieve the order from the database based on the provided ID
        return orderRepository.findById(orderId);
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        // Retrieve orders associated with the given customer ID
        return orderRepository.findByCustomerId(customerId);
    }
    
    // Other service methods as needed
}
