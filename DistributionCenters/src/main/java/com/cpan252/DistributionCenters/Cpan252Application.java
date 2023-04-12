package com.cpan252.DistributionCenters;
import com.cpan252.DistributionCenters.model.DistributionCenter;
import com.cpan252.DistributionCenters.model.Item;
import com.cpan252.DistributionCenters.repository.DistributionCentreRepository;
import com.cpan252.DistributionCenters.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Cpan252Application {
    public static void main(String[] args) {
        SpringApplication.run(Cpan252Application.class, args);
    }

    @Bean
    public CommandLineRunner initData(DistributionCentreRepository distributionCentreRepository, ItemRepository itemRepository) {
        return (args) -> {
            // Create 4 distribution centers
            DistributionCenter dc1 = new DistributionCenter("DC1", 0, 40,70);
            DistributionCenter dc2 = new DistributionCenter("DC2", 0, 30, 120);
            DistributionCenter dc3 = new DistributionCenter("DC3", 0, 30, 20);
            DistributionCenter dc4 = new DistributionCenter("DC4", 0, 60, -80);
            DistributionCenter wh = new DistributionCenter("WH", 0, 50, 50);
            wh.setIsWarehouse(true);

            // Save distribution centers
            distributionCentreRepository.save(dc1);
            distributionCentreRepository.save(dc2);
            distributionCentreRepository.save(dc3);
            distributionCentreRepository.save(dc4);
            System.out.println(dc1);
            System.out.println(dc2);
            System.out.println(dc3);
            System.out.println(dc4);

            // Create 3 items for each distribution center
            Item item1 = new Item("Item1", 2023, 1000.0, Item.Brand.GUCCI, 3, dc1);
            Item item2 = new Item("Item2", 2023, 1200.0, Item.Brand.BALENCIAGA, 3, dc1);
            Item item3 = new Item("Item3", 2023, 1300.0, Item.Brand.DIOR, 3, dc1);

            Item item4 = new Item("Item4", 2023, 1100.0, Item.Brand.GUCCI, 3, dc2);
            Item item5 = new Item("Item5", 2023, 1400.0, Item.Brand.BALENCIAGA, 3, dc2);
            Item item6 = new Item("Item6", 2023, 1500.0, Item.Brand.DIOR, 3, dc2);

            Item item7 = new Item("Item7", 2023, 1600.0, Item.Brand.GUCCI, 3, dc3);
            Item item8 = new Item("Item8", 2023, 1700.0, Item.Brand.BALENCIAGA, 3, dc3);
            Item item9 = new Item("Item9", 2023, 1800.0, Item.Brand.DIOR, 3, dc3);

            Item item10 = new Item("Item10", 2023, 1900.0, Item.Brand.GUCCI, 3, dc4);
            Item item11 = new Item("Item11", 2023, 1100.0, Item.Brand.BALENCIAGA, 3, dc4);
            Item item12 = new Item("Item12", 2023, 1200.0, Item.Brand.DIOR, 3, dc4);

            // Save items
            itemRepository.save(item1);
            itemRepository.save(item2);
            itemRepository.save(item3);
            itemRepository.save(item4);
            itemRepository.save(item5);
            itemRepository.save(item6);
            itemRepository.save(item7);
            itemRepository.save(item8);
            itemRepository.save(item9);
            itemRepository.save(item10);
            itemRepository.save(item11);
            itemRepository.save(item12);
            System.out.println(item3);
            System.out.println(item6);
            System.out.println(item9);
            System.out.println(item12);
        };
    }
}
