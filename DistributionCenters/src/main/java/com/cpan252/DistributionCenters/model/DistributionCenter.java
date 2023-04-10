package com.cpan252.DistributionCenters.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
public class DistributionCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private int itemsAvailable;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @OneToMany(mappedBy = "distributionCenter", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public DistributionCenter(String name, int itemsAvailable, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.itemsAvailable = itemsAvailable;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    // Add getters and setters here

    public void addItem(Item item) {
        items.add(item);
        item.setDistributionCenter(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setDistributionCenter(null);
    }
}

