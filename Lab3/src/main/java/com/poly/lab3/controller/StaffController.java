package com.poly.lab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.poly.lab3.model.Staff;

import java.util.Date;
import java.util.List;

@Controller
public class StaffController {

    // Bài 1: hiển thị 1 staff
    @RequestMapping("/staff/detail")
    public String detail(Model model) {
        Staff staff = Staff.builder()
                .id("quangsang@gmail.com")
                .fullname("Vo Quang Sang")
                .photo("img/qs.jpg")
                .gender(true)
                .birthday(new Date())
                .salary(12345.6789)
                .level(2)
                .build();
        model.addAttribute("staff", staff);
        return "staff-detail";
    }

    // Bài 2: hiển thị list staff
    @RequestMapping("/staff/list")
    public String list(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("quangsang1@gmail.com").fullname("Vo Quang Sang 1").level(0).build(),
                Staff.builder().id("quangsang2@gmail.com").fullname("Vo Quang Sang 2").level(1).build(),
                Staff.builder().id("quangsang3@gmail.com").fullname("Vo Quang Sang 3").level(2).build(),
                Staff.builder().id("quangsang4@gmail.com").fullname("Vo Quang Sang 4").level(2).build(),
                Staff.builder().id("quangsang5@gmail.com").fullname("Vo Quang Sang 5").level(1).build(),
                Staff.builder().id("quangsang6@gmail.com").fullname("Vo Quang Sang 6").level(0).build()
        );
        model.addAttribute("list", list);
        return "staff-list";
    }

    // Bài 4: list với trạng thái
    @RequestMapping("/staff/list-status")
    public String listStatus(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("quangsang1@gmail.com").fullname("Vo Quang Sang 1").level(0).build(),
                Staff.builder().id("quangsang2@gmail.com").fullname("Vo Quang Sang 2").level(1).build(),
                Staff.builder().id("quangsang3@gmail.com").fullname("Vo Quang Sang 3").level(2).build(),
                Staff.builder().id("quangsang4@gmail.com").fullname("Vo Quang Sang 4").level(2).build(),
                Staff.builder().id("quangsang5@gmail.com").fullname("Vo Quang Sang 5").level(1).build(),
                Staff.builder().id("quangsang6@gmail.com").fullname("Vo Quang Sang 6").level(0).build()
        );
        model.addAttribute("list", list);
        return "list-status";
    }

    // Bài 5: list vào select box và radio
    @RequestMapping("/staff/list-controls")
    public String listControls(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("quangsang1@gmail.com").fullname("Vo Quang Sang 1").level(0).build(),
                Staff.builder().id("quangsang2@gmail.com").fullname("Vo Quang Sang 2").level(1).build(),
                Staff.builder().id("quangsang3@gmail.com").fullname("Vo Quang Sang 3").level(2).build(),
                Staff.builder().id("quangsang4@gmail.com").fullname("Vo Quang Sang 4").level(2).build(),
                Staff.builder().id("quangsang5@gmail.com").fullname("Vo Quang Sang 5").level(1).build(),
                Staff.builder().id("quangsang6@gmail.com").fullname("Vo Quang Sang 6").level(0).build()
        );
        model.addAttribute("list", list);
        return "list-controls";
    }
}
