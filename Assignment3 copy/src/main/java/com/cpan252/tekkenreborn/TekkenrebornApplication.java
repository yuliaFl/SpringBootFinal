package com.cpan252.tekkenreborn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cpan252.tekkenreborn.model.Clothing;
import com.cpan252.tekkenreborn.model.Clothing.Brand;
import com.cpan252.tekkenreborn.repository.ClothingRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class TekkenrebornApplication implements CommandLineRunner {

    @Autowired
    private ClothingRepository clothingRepository;

    public static void main(String[] args) {
        SpringApplication.run(TekkenrebornApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Clothing item1 = new Clothing("Bag", 2022, 3456, Brand.GUCCI);
        Clothing item2 = new Clothing("purse", 2022,4567,Brand.DIOR);
        Clothing item3 = new Clothing("shoe", 2023,5555,Brand.GUCCI);

        clothingRepository.save(item1);
        clothingRepository.save(item2);
        clothingRepository.save(item3);
    }
}