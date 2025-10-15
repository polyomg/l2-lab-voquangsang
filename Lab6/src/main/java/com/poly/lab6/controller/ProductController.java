package com.poly.lab6.controller;

import com.poly.lab6.DAO.ProductDAO;
import com.poly.lab6.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ProductController {
    @Autowired
    ProductDAO dao;

    // 🟢 Bài 4 - Sắp xếp
    @RequestMapping("/product/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
        model.addAttribute("field", field.orElse("price").toUpperCase());
        model.addAttribute("items", dao.findAll(sort));
        return "sort";
    }

    // 🟢 Bài 5 - Phân trang
    @RequestMapping("/product/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        return "page";
    }
}
