package com.cpan252.DistributionCenters.model;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ItemDTO {
    private String name;
    private Brand brand; // change this line

    public enum Brand {
        GUCCI,
        BALENCIAGA,
        DIOR,
        STONE_ISLAND,
        // Add any additional brands here
    }
}
