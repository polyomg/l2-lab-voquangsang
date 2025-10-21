package com.poly.lab7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.lab7.dao.ProductDAO;
import com.poly.lab7.entity.Report;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ProductDAO dao;

    @RequestMapping("/inventory-by-category")
    public String inventory(Model model) {
        List<Report> items = dao.getInventoryByCategory();
        model.addAttribute("items", items);
        return "inventory-by-category";
    }
}
