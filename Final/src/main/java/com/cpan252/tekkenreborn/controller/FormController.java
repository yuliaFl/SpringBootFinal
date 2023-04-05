package com.cpan252.tekkenreborn.controller;

import java.util.EnumSet;

import com.cpan252.tekkenreborn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cpan252.tekkenreborn.model.Clothing;
import com.cpan252.tekkenreborn.model.Clothing.Brand;
import com.cpan252.tekkenreborn.repository.ClothingRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/form")
public class FormController {

    @Autowired
    private ClothingRepository clothingRepository;

    @GetMapping
    public String design() {
        return "form";
    }

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
        log.info("brands converted to string:  {}", brands);
    }

    @ModelAttribute
    public Clothing clothing() {
        return Clothing
                .builder()
                .build();
    }

    @PostMapping
    public String processClothingAddition(@Valid Clothing clothing, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        log.info("Processing clothing: {}", clothing);
        clothingRepository.save(clothing);
        return "redirect:/clothinglist";
    }

    @PostMapping("/deleteAllClothings")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String processClothingDeletion(@AuthenticationPrincipal User user) {
        log.info("Deleting all clothing for user: {}", user.getAuthorities());
        clothingRepository.deleteAll();
        return "redirect:/design";
    }

}
