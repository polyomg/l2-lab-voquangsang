package com.poly.lab8.controller;

import com.poly.lab8.entity.Account;
import com.poly.lab8.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    // ✅ Hiển thị form login
    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        return "/login";
    }

    // ✅ Xử lý đăng nhập
    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        Account user = accountService.findByUserName(username);

        // Sai username
        if (user == null) {
            model.addAttribute("message", "Invalid username!");
            return "/login";
        }

        // Sai password
        if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
            return "/login";
        }

        // ✅ Đăng nhập thành công
        session.setAttribute("user", user);
        model.addAttribute("message", "Login successfully!");

        // Nếu có URI bị chặn trước đó thì quay lại
        String securityUri = (String) session.getAttribute("securityUri");
        if (securityUri != null) {
            session.removeAttribute("securityUri");
            return "redirect:" + securityUri;
        }

        // ✅ Nếu không có URI trước đó, chuyển hướng theo vai trò
        if (user.isAdmin()) {
            return "redirect:/admin/home/index";
        } else {
            return "redirect:/account/edit-profile";
        }
    }

    // ✅ Các endpoint test (bảo vệ bằng interceptor)
    @GetMapping({"/account/change-password", "/account/edit-profile", "/admin/**", "/order/**"})
    @ResponseBody
    public String hello() {
        return "Hello world!";
    }
}
