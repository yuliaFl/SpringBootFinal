package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.DistributionCenter;
import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.model.Item.Brand;
import com.cpan252.DistributionCenters.model.ItemDTO;
import com.cpan252.DistributionCenters.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemRepository itemRepository;

    @Autowired
    private DistributionCentreRepository distributionCenterRepository;

    @GetMapping // Change this line
    public String showDistributionCentersAndItems(Model model) {
        List<DistributionCenter> distCenters = StreamSupport.stream(distributionCenterRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        model.addAttribute("distCenters", distCenters);

        List<Item> items = (List<Item>) itemRepository.findAll();
        model.addAttribute("items", items);

        return "items";
    }

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @ModelAttribute
    public void itemDto(Model model) {
        model.addAttribute("itemDTO", new ItemDTO()); // change "ItemDTO" to "itemDTO"
    }


}
