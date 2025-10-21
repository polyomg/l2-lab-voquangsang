package com.poly.lab5.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.poly.lab5.model.Item;
import com.poly.lab5.service.ShoppingCartService;

@Controller
public class ShopController {

    // Dữ liệu mẫu (tạm thay cho DB.java)
    public static final Map<Integer, Item> ITEMS = new HashMap<>();
    static {
        ITEMS.put(1, new Item(1, "Samsung", 10.0, 0));
        ITEMS.put(2, new Item(2, "Nokia 2021", 20.50, 0));
        ITEMS.put(3, new Item(3, "iPhone 20", 90.49, 0));
        ITEMS.put(4, new Item(4, "Motorola", 15.55, 0));
        ITEMS.put(5, new Item(5, "Seamen", 8.99, 0));
    }

    @Autowired ShoppingCartService cart;

    @RequestMapping("/item/index")
    public String list(Model model) {
        model.addAttribute("items", ITEMS.values());
        return "indexItems";
    }

    @RequestMapping("/cart/view")
    public String view(Model model) {
        model.addAttribute("cart", cart);
        // Trả về indexCart.html
        return "indexCart";
    }

    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable Integer id) {
        cart.add(id);
        return "redirect:/cart/view";
    }

    @RequestMapping("/cart/remove/{id}")
    public String remove(@PathVariable Integer id) {
        cart.remove(id);
        return "redirect:/cart/view";
    }

    @RequestMapping("/cart/update/{id}")
    public String update(@PathVariable Integer id, @RequestParam("qty") Integer qty) {
        cart.update(id, qty);
        return "redirect:/cart/view";
    }

    @RequestMapping("/cart/clear")
    public String clear() {
        cart.clear();
        return "redirect:/cart/view";
    }
}
