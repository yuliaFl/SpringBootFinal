package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.DistributionCenter;
import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.repository.*;
import com.cpan252.DistributionCenters.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String getAllItems(Model model) {
        List<Item> items = (List<Item>) itemRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }
}
