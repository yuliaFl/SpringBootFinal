package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.DistributionCenter;
import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.model.ItemDTO;
import com.cpan252.DistributionCenters.repository.DistributionCentreRepository;
import com.cpan252.DistributionCenters.repository.ItemRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final DistributionCentreRepository centerRepository;
    private final ItemRepository itemRepository;

    public AdminController(DistributionCentreRepository centerRepository, ItemRepository itemRepository) {
        this.centerRepository = centerRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/distribution-center")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showAdminDistributionCenterPage(Model model) {
        model.addAttribute("itemDTO", new ItemDTO());
        return "admin-distribution-center";
    }

    @ModelAttribute("warehouseItems")
    public List<Item> getWarehouseItems() {
        DistributionCenter warehouse = centerRepository.findByIsWarehouseTrue();
        return itemRepository.findByDistributionCenter(warehouse);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371.01;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }
    @PostMapping("/request-item")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String requestItem(@RequestParam("name") String name,
                              @RequestParam("brand") String brand,
                              RedirectAttributes redirectAttributes) {
        System.out.println("Request item started");
        List<Item> items = itemRepository.findByBrandFromAndName(Item.Brand.valueOf(brand.toUpperCase()), name);
        if (items.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Stock can't be replenished");
            return "redirect:/error";
        }

        DistributionCenter warehouse = centerRepository.findByIsWarehouseTrue();
        List<DistributionCenter> centers = centerRepository.findAllByIsWarehouseFalse();

        DistributionCenter closestCenter = null;
        double closestDistance = Double.MAX_VALUE;

        for (DistributionCenter center : centers) {
            for (Item item : items) {
                if (item.getDistributionCenter().getId() == center.getId() && item.getItemsAvailable() > 0) {
                    double distance = calculateDistance(warehouse.getLatitude(), warehouse.getLongitude(),
                            center.getLatitude(), center.getLongitude());
                    if (distance < closestDistance) {
                        System.out.println("New closest center found");
                        closestDistance = distance;
                        closestCenter = center;
                    }
                }
            }
        }

        if (closestCenter != null) {
            System.out.println("Closest center is not null");
            final Long closestCenterId = closestCenter.getId();

            Item itemToReplenish = items.stream()
                    .filter(item -> item.getDistributionCenter().getId() == closestCenterId)
                    .findFirst()
                    .orElse(null);

            if (itemToReplenish != null) {
                System.out.println("Item to replenish found");
                itemToReplenish.setItemsAvailable(itemToReplenish.getItemsAvailable() - 1);
                itemRepository.save(itemToReplenish);
                Item newItem = new Item(itemToReplenish.getName(), itemToReplenish.getYear(),
                        itemToReplenish.getPrice(), itemToReplenish.getBrand(), 1, warehouse);
                itemRepository.save(newItem);

                return "redirect:/items";
            }
        }

        redirectAttributes.addFlashAttribute("errorMessage", "Stock can't be replenished");
        return "redirect:/error";
    }

}
