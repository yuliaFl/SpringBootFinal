package com.cpan252.DistributionCenters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout-page")
    public String logoutPage() {
        return "logout";
    }
}
