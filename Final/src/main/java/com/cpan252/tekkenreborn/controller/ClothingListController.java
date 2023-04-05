package com.cpan252.tekkenreborn.controller;

import com.cpan252.tekkenreborn.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cpan252.tekkenreborn.model.ClothingDto;
import com.cpan252.tekkenreborn.repository.ClothingRepository;
import com.cpan252.tekkenreborn.repository.ClothingPaginated;

@Controller
@RequestMapping("/clothinglist")
public class ClothingListController {
    private ClothingRepository clothingRepository;

    private ClothingPaginated clothingPaginated;

    public ClothingListController(ClothingRepository clothingRepository,
                                 ClothingPaginated clothingPaginated) {
        this.clothingRepository = clothingRepository;
        this.clothingPaginated = clothingPaginated;
    }

    @GetMapping
    public String showClothings(Model model) {
        model.addAttribute("clothings", clothingRepository.findAll());
        return "clothinglist";
    }
    @ModelAttribute
    public void clothings(Model model) {
        model.addAttribute("clothings", clothingRepository.findAll());
    }

    @ModelAttribute
    public void clothingDto(Model model) {
        model.addAttribute("clothingDto", new ClothingDto());
    }

    @PostMapping
    public String searchFightersByDate(@ModelAttribute ClothingDto clothingDto, Model model) {
        model.addAttribute("clothings", clothingRepository.findByNameStartsWith(
                clothingDto.getName()));
        return "clothinglist";
    }
}
