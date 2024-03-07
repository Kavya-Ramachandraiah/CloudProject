package com.karthik.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

     @Value("${registration.success.message}")
    private String registrationSuccessMessage;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String signup(@ModelAttribute Customer user, Model model) {
        userService.createCustomer(user);
        model.addAttribute("successMessage", registrationSuccessMessage);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/getallcustomers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = userService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "all-customers";
    }

    @GetMapping("/getcustomerbyid/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        Customer customer = userService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer-details";
    }

    @PutMapping("/updatecustomer/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer user) {
        userService.updateCustomer(id, user);
        return "redirect:/getallcustomers";
    }

    @DeleteMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
        return "redirect:/getallcustomers";
    }
}
