package com.cpan252.tekkenreborn.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cpan252.tekkenreborn.model.Clothing;
import com.cpan252.tekkenreborn.model.Clothing.Brand;
import org.springframework.stereotype.Repository;
@Repository
public interface ClothingRepository extends CrudRepository<Clothing, Long> {
    List<Clothing> findByBrandFrom(Brand brand);

    List<Clothing> findByNameStartsWith(String name);
}
