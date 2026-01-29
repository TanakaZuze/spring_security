package dev.tanaka.spring_security.controller;

import dev.tanaka.spring_security.entity.User;
import dev.tanaka.spring_security.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticationController {
    private final CustomUserDetailsService userDetailsService;

    public AuthenticationController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes){
        userDetailsService.registerUser(user);
        redirectAttributes.addFlashAttribute("message",
                "Please confirm your email address");

        return "redirect:/register";
    }
}
