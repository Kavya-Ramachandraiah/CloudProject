package com.karthik.cloudkitchenapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karthik.cloudkitchenapplication.entity.Caterer;
import com.karthik.cloudkitchenapplication.service.CatererService;
@RestController
@RequestMapping("/caterers")
public class CatererController {
    @Autowired
    private CatererService userService;

    @GetMapping("/getallcaterers")
    public List<Caterer> getAllCaterers() {
        return userService.getAllCaterers();
    }

    @GetMapping("/getcatererbyid/{id}")
    public Caterer getCatererById(@PathVariable Long id) {
        return userService.getCatererById(id);
    }

    @PostMapping("/savecaterer")
    public Caterer createCaterer(@RequestBody Caterer user) {
        return userService.createCaterer(user);
    }

    @PutMapping("/updatecaterer/{id}")
    public Caterer updateCaterer(@PathVariable Long id, @RequestBody Caterer user) {
        return userService.updateCaterer(id, user);
    }

    @DeleteMapping("/deletecaterer/{id}")
    public void deleteCaterer(@PathVariable Long id) {
        userService.deleteCaterer(id);
    }
}
