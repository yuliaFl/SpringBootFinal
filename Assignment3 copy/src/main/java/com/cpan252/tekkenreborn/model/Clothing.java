package com.cpan252.tekkenreborn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Clothing")
public class Clothing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @Min(2021)
    private int year1;
    @DecimalMin(value = "1000.0")
    private double price;
    private Brand brandFrom;

    public enum Brand {
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior"), GUCCI("Gucci"),
        LOUIS_VUITTON("Louis Vuitton"), ROLEX("Rolex");

        private String title;

        private Brand(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public Clothing(String name, int year, double price, Brand brandFrom ){
        this.name = name;
        this.year1 = year;
        this.price=price;
        this.brandFrom= brandFrom;
    }
}

