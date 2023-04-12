package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.repository.DistributionCentreRepository;
import com.cpan252.DistributionCenters.repository.ItemRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAdminDistributionCenterPage() {
        return "admin-distribution-center";
    }

    @PostMapping("/request-item")
    public String requestItem(@RequestParam("name") String name,
                              @RequestParam("brand") String brand,
                              RedirectAttributes redirectAttributes) {
        List<Item> items = itemRepository.findByBrandFromAndName(Item.Brand.valueOf(brand.toUpperCase()), name);
        if (items.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Stock can't be replenished");
            return "redirect:/error";
        }

        // Logic to find the closest distribution center and update the stock
        // ...

        return "redirect:/items";
    }
}
