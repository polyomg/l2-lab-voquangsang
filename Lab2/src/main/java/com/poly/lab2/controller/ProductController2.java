package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.poly.lab2.model.Product;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product2")
public class ProductController2 {

    private static final List<Product> items = new ArrayList<>();

    static {
        items.add(new Product("A", 1.0));
        items.add(new Product("B", 12.0));
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("p2", new Product());
        model.addAttribute("items", items);
        return "product2";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("p2") Product product, Model model) {
        items.add(product);

        model.addAttribute("items", items);
        return "product2";
    }

    @ModelAttribute("p1")
    public Product getDefaultP1() {
        return new Product("iPhone 30", 5000.0);
    }

    @ModelAttribute("p3")
    public Product getDefaultP3() {
        return new Product("Samsung S25", 999.9);
    }
}
