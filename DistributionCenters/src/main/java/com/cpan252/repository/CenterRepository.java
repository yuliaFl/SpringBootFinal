package com.cpan252.repository;

import com.cpan252.model.DistributionCenter;
import com.cpan252.model.Item;
import com.cpan252.model.Item.Brand;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
/*
CrudRepository does all the add item to distribution centre, delete item, list all distribution
centres,
"request item by brand and name"
 */
public interface CenterRepository extends CrudRepository <Item, Long> {
    List<Item> findByBrandFrom(Brand brand);
    List<Item> findByNameStartsWith(String name);
}
