package com.cpan252.DistributionCenters.repository;

import com.cpan252.DistributionCenters.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findByBrandFromAndName(Item.Brand brand, String name);
}
