package com.poly.lab1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    HttpServletRequest request;

    @GetMapping("/login/form")
    public String form() {
        return "new";
    }

    @PostMapping("/login/check")
    public String login(Model model) {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if ("poly".equals(user) && "123".equals(pass)) {
            model.addAttribute("message", "Đăng nhập thành công!");
            return "redirect:/poly/hello";
        } else {
            model.addAttribute("message", "Sai username hoặc password!");
            return "login";
        }

    }
}
