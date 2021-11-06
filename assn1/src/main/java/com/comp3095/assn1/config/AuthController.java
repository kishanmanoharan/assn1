package com.comp3095.assn1.config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/")
    public String home() {
        return "redirect:/signin";
    }
}
