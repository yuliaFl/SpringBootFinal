package com.cpan252.model;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/*
This file is for "distribution centre (id, name, items available, latitude,
longitude)"
 */
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
}
