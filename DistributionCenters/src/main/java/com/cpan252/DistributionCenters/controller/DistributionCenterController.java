package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.DistributionCenter;
import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.repository.DistributionCentreRepository;
import com.cpan252.DistributionCenters.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/distribution-centres")
public class DistributionCenterController {
    private final DistributionCentreRepository centerRepository;
    private final ItemRepository itemRepository;

    public DistributionCenterController(DistributionCentreRepository centerRepository, ItemRepository itemRepository) {
        this.centerRepository = centerRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping("/{id}/items")
    public DistributionCenter addItemToDistributionCenter(@PathVariable Long id, @RequestBody Item item) {
        DistributionCenter distributionCenter = centerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Distribution Center not found"));
        distributionCenter.addItem(item);
        itemRepository.save(item);
        return centerRepository.save(distributionCenter);
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteItem(@PathVariable Integer itemId) {
        itemRepository.deleteById(itemId);
    }

    @GetMapping
    public List<DistributionCenter> getAllDistributionCenters() {
        return (List<DistributionCenter>) centerRepository.findAll();
    }

    @GetMapping("/items")
    public List<Item> findItemsByBrandAndName(@RequestParam("brand") String brand, @RequestParam("name") String name) {
        return itemRepository.findByBrandFromAndName(Item.Brand.valueOf(brand.toUpperCase()), name);
    }
}


