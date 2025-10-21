package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.poly.lab2.model.Product;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/form")
    public String form() {
        return "product"; // vì bạn có file templates/product.html
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        return "product"; // cũng phải trùng với product.html
    }
}
