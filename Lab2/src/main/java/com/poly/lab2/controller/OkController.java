package com.poly.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/ctrl")
public class OkController {
        // POST /ctrl/ok with no "x" parameter -> OK 1
        @PostMapping(value = "/ok", params = "!x")
        public String m1(Model model) {
            model.addAttribute("method", "m1");
            return "ok";
        }

        // GET /ctrl/ok  -> OK 2
        @GetMapping("/ok")
        public String m2(Model model) {
            model.addAttribute("method", "m2");
            return "ok";
        }

        // POST /ctrl/ok?x  (parameter "x" присутствует) -> OK 3
        @PostMapping(value = "/ok", params = "x")
        public String m3(Model model) {
            model.addAttribute("method", "m3");
            return "ok";
        }
    }

