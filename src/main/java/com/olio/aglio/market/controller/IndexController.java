package com.olio.aglio.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Aglio E Olio");
        model.addAttribute("text", "장스방은 스방스방합니다");
        return "index";
    }
}
