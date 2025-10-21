package com.poly.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/poly/hello")
    public String sayHello(Model model) {
        model.addAttribute("title", "Võ Quang Sang");
        model.addAttribute("subject", "Ts01128");
        return "hello";
    }
}
