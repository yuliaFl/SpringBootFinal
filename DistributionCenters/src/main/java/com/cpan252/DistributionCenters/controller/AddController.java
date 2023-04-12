package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.repository.ItemRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.EnumSet;

@Controller
@Slf4j
@RequestMapping("/add")
public class AddController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String add(){ return "add";}

    @ModelAttribute
    public void brands(Model model) {
        var brands = EnumSet.allOf(Item.Brand.class);
        model.addAttribute("brands", brands);
        log.info("brands converted to string:  {}", brands);
    }

    @ModelAttribute
    public Item item() {
        return Item
                .builder()
                .build();
    }
    @PostMapping
    public String processItemAddition(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        System.out.println("hi");
        log.info("Processing item: {}", item);
        System.out.println("hi2");
        itemRepository.save(item);
        System.out.println("clothing saved");
        return "redirect:/items";
    }

}
