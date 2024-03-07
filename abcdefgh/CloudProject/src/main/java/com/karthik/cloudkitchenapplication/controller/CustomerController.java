package com.karthik.cloudkitchenapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public String getAllCustomers(Model model) {
    List<Customer> customers = userService.getAllCustomers();
    model.addAttribute("customers", customers);
    return "all-customers"; // This corresponds to the Thymeleaf template name
}

@GetMapping("/getcustomerbyid/{id}")
public String getCustomerById(@PathVariable Long id, Model model) {
    Customer customer = userService.getCustomerById(id);
    model.addAttribute("customer", customer);
    return "customer-details"; // This corresponds to the Thymeleaf template name
}

@PutMapping("/updatecustomer/{id}")
public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer user) {
    userService.updateCustomer(id, user);
    return "redirect:/getallcustomers"; // Redirects to the URL mapped to getAllCustomers method
}

@DeleteMapping("/deletecustomer/{id}")
public String deleteCustomer(@PathVariable Long id) {
    userService.deleteCustomer(id);
    return "redirect:/getallcustomers"; // Redirects to the URL mapped to getAllCustomers method
}

}
