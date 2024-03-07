package com.karthik.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.karthik.cloudkitchenapplication.entity.Customer;
import com.karthik.cloudkitchenapplication.service.CustomerService;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService userService;

    

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        userService.createCustomer(customer);
        String registrationSuccessMessage = "Registration successful. Please login.";
        model.addAttribute("successMessage", registrationSuccessMessage);
        return "login";
    }
    


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

   @GetMapping("/getallcustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = userService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/getcustomerbyid/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = userService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok().body(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer user) {
        Customer updatedCustomer = userService.updateCustomer(id, user);
        if (updatedCustomer != null) {
            return ResponseEntity.ok().body(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
