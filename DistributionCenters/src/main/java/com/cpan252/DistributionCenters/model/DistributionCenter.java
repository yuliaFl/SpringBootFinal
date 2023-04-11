package com.cpan252.DistributionCenters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DISTRIBUTIONCENTER")
public class DistributionCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Min(0)
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

    // Add the getter for the items field
    public List<Item> getItems() {
        return items;
    }

    // Add other getters and setters here

    public void addItem(Item item) {
        items.add(item);
        item.setDistributionCenter(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setDistributionCenter(null);
    }

    public DistributionCenter() {
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getItemsAvailable() {
        return itemsAvailable;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

}
