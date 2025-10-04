package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/form")
    public String form() {
        // chỉ load view form.html
        return "form";
    }

    @RequestMapping(value = "/save/{x}", method = RequestMethod.POST)
    public String save(@PathVariable("x") String x,
                       @RequestParam("y") String y,
                       Model model) {
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        // view đang nằm trực tiếp trong templates/, không có thư mục con
        return "form";
    }
}
