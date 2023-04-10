package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.RegisterForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan252.DistributionCenters.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "/register";
    }

    @PostMapping
    public String registerUserAccount(RegisterForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/";
    }
}
