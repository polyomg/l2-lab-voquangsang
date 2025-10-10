package com.poly.lab5.controller;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.poly.lab5.service.UtilityService.CookieService;
import com.poly.lab5.service.UtilityService.ParamService;
import com.poly.lab5.service.UtilityService.SessionService;

@Controller
public class AccountController {

    @Autowired CookieService cookie;
    @Autowired ParamService param;
    @Autowired SessionService session;

    @GetMapping("/account/login")
    public String login1(Model model) {
        model.addAttribute("remembered", cookie.getValue("user"));
        model.addAttribute("username", session.get("username")); // ✅ thêm để Thymeleaf dùng
        return "login";
    }

    @PostMapping("/account/login")
    public String login2(Model model) {
        String un = param.getString("username", "");
        String pw = param.getString("password", "");
        boolean rm = param.getBoolean("remember", false);

        if ("poly".equals(un) && "123".equals(pw)) {
            session.set("username", un);
            if (rm) cookie.add("user", un, 24 * 10);
            else cookie.remove("user");
            model.addAttribute("message", "Đăng nhập thành công!");
            model.addAttribute("username", un); // ✅ thêm dòng này
        } else {
            model.addAttribute("message", "Sai tài khoản hoặc mật khẩu!");
        }

        return "login";
    }

    // Đăng ký + upload ảnh
    @GetMapping("/account/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/account/register")
    public String registerSave(@RequestParam("photo") MultipartFile file, Model model) {
        File saved = param.save(file, "/uploads");
        model.addAttribute("msg",
                (saved != null) ? "Ảnh đã lưu: " + saved.getName() : "Không có file upload");
        return "register";
    }
}
