package com.cpan252.tekkenreborn.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class ManagementController {
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String design() {
        return "management";
    }

}
