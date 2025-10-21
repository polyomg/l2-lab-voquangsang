package com.poly.lab7.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poly.lab7.dao.ProductDAO;
import com.poly.lab7.entity.Product;
import com.poly.lab7.service.SessionService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO dao;

    @Autowired
    SessionService session;

    // Bài 1: Tìm theo khoảng giá (JPQL)
    @RequestMapping("/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPrice(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "search";
    }

    // Bài 2: Tìm theo từ khóa + phân trang (JPQL)
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String keywords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", keywords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findByKeywords("%" + keywords + "%", pageable);
        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);
        return "search-and-page";
    }

    // Bài 4: DSL tìm theo khoảng giá
    @RequestMapping("/search-dsl")
    public String searchDSL(Model model,
                            @RequestParam("min") Optional<Double> min,
                            @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "search";
    }

    // Bài 5: DSL tìm theo từ khóa + phân trang
    @RequestMapping("/search-and-page-dsl")
    public String searchAndPageDSL(Model model,
                                   @RequestParam("keywords") Optional<String> kw,
                                   @RequestParam("p") Optional<Integer> p) {
        String keywords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", keywords);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAllByNameLike("%" + keywords + "%", pageable);
        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);
        return "search-and-page";
    }
}
