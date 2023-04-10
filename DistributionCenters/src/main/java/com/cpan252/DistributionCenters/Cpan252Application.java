package com.cpan252.DistributionCenters;

import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.model.DistributionCenter;
import com.cpan252.DistributionCenters.repository.DistributionCentreRepository;
import com.cpan252.DistributionCenters.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Cpan252Application {
    public static void main(String[] args) {
        SpringApplication.run(Cpan252Application.class, args);
    }


}
