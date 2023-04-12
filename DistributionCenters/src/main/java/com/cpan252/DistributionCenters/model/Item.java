package com.cpan252.DistributionCenters.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @Min(2021)
    @Column(name = "production_year")
    private int year;
    @DecimalMin(value = "1000.0")
    private double price;
    @Column(name = "brand_from")
    private Brand brandFrom;
    @NotNull
    @Min(0)
    @Column(name = "items_available")
    private int itemsAvailable ;

    public enum Brand {
        GUCCI,
        BALENCIAGA,
        DIOR,
        STONE_ISLAND,
        // Add any additional brands here
    }


    public Item(String name, int year, double price, Brand brandFrom, int itemsAvailable , DistributionCenter distributionCenter) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.brandFrom = brandFrom;
        this.itemsAvailable  = itemsAvailable ;
        this.distributionCenter = distributionCenter;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distribution_center_id")
    private DistributionCenter distributionCenter;

    // Add getters and setters here

    public void setDistributionCenter(DistributionCenter distributionCenter) {
        this.distributionCenter = distributionCenter;
    }

    public Brand getBrand() {
        return brandFrom;
    }
}