package com.poly.lab4.controller;

import com.poly.lab4.model.Staff;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AppController {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @GetMapping({"/", "/home/index"})
    public String home() {
        return "index";
    }

    @GetMapping("/home/about")
    public String about() {
        return "about";
    }

    @GetMapping("/staff/create/form")
    public String createForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        ensureUploadDir();
        return "staff-create";
    }

    @PostMapping("/staff/create/save")
    public String createSave(Model model,
                             @Valid @ModelAttribute("staff") Staff staff,
                             Errors errors,
                             @RequestPart(value = "photo_file", required = false) MultipartFile photoFile) throws IOException {

        if (photoFile != null && !photoFile.isEmpty()) {
            String filename = System.currentTimeMillis() + "_" +
                    photoFile.getOriginalFilename().replaceAll("\\s+", "_");
            Path target = Paths.get(uploadDir).resolve(filename);
            Files.createDirectories(target.getParent());
            photoFile.transferTo(target.toFile());
            staff.setPhoto(filename);
        }

        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
        } else {
            model.addAttribute("message", "Dữ liệu đã nhập đúng!");
        }

        model.addAttribute("staff", staff);
        return "staff-validate";
    }

    private void ensureUploadDir() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException ignored) {
        }
    }
}
