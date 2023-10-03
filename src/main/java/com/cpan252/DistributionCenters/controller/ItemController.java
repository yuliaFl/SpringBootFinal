package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.DistributionCenter;
import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.model.Item.Brand;
import com.cpan252.DistributionCenters.model.ItemDTO;
import com.cpan252.DistributionCenters.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/items")
@PreAuthorize("hasRole('ROLE_ADMIN')")
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
 @GetMapping("/{distCenterId}/add-item")
    public String showAddItemForm(@PathVariable("distCenterId") Long distCenterId, Model model) {
        DistributionCenter distCenter = distributionCenterRepository.findById(distCenterId).orElse(null);
        if (distCenter == null) {
            return "redirect:/items";
        }
        model.addAttribute("distCenter", distCenter);
        model.addAttribute("item", new Item());
        return "add-item";
    }

    @PostMapping("/{distCenterId}/delete-item/{itemId}")
    public String deleteItem(@PathVariable("distCenterId") Long distCenterId, @PathVariable("itemId") int itemId) {
        DistributionCenter distCenter = distributionCenterRepository.findById(distCenterId).orElse(null);
        Item item = itemRepository.findById(itemId).orElse(null);
        if (distCenter != null && item != null && distCenter.getItems().contains(item)) {
            distCenter.removeItem(item);
            distributionCenterRepository.save(distCenter);
            itemRepository.delete(item);
        }
        return "redirect:/items";
    }

    @PostMapping("/{distCenterId}/add-item")
    public String addItem(@PathVariable("distCenterId") Long distCenterId, @ModelAttribute Item item) {
        DistributionCenter distCenter = distributionCenterRepository.findById(distCenterId).orElse(null);
        if (distCenter != null) {
            distCenter.addItem(item);
            distributionCenterRepository.save(distCenter);
        }
        return "redirect:/items";
    }

}
