package com.cpan252.DistributionCenters.repository;

import com.cpan252.DistributionCenters.model.*;
import com.cpan252.DistributionCenters.model.Item.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findByBrandFromAndName(Brand brand, String name);
    List<Item> findByDistributionCenter(DistributionCenter distributionCenter);
}
