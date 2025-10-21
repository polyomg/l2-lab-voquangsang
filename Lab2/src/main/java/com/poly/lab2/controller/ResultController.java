package com.poly.lab2.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResultController {

    @RequestMapping("/a")
    public String m1() {
        return "a"; // a.html
    }

    @RequestMapping("/b")
    public String m2(Model model) {
        model.addAttribute("message", "I come from b");
        // muốn giữ message trong model khi chuyển đến /a -> dùng forward
        return "forward:/a";
    }

    @RequestMapping("/c")
    public String m3(RedirectAttributes params) {
        params.addAttribute("message", "I come from c");
        return "redirect:/a";
    }

    // ?3: để hiển thị trực tiếp chuỗi trả về, dùng @ResponseBody
    @ResponseBody
    @RequestMapping("/d")
    public String m4() {
        return "I come from d";
    }
}