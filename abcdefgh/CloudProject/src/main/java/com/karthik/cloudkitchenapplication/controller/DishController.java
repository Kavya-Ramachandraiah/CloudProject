package com.karthik.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.karthik.cloudkitchenapplication.entity.Dish;
import com.karthik.cloudkitchenapplication.entity.Order;
import com.karthik.cloudkitchenapplication.service.DishService;
import com.karthik.cloudkitchenapplication.service.OrderService;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private OrderService orderService; // Inject OrderService

    @PostMapping("/save")
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish) {
        // Retrieve the order from the database based on the provided orderId
        Order order = orderService.getOrderById(dish.getOrder().getId()).orElseThrow(() -> new RuntimeException("Order not found"));
    
        // Set the order in the dish object
        dish.setOrder(order);
    
        Dish savedDish = dishService.saveDish(dish);
        
        return new ResponseEntity<>(savedDish, HttpStatus.CREATED);
    }


    // Other controller methods as needed
}
