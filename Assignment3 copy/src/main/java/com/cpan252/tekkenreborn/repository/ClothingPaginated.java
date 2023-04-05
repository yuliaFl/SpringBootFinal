package com.cpan252.tekkenreborn.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cpan252.tekkenreborn.model.Clothing;

public interface ClothingPaginated extends PagingAndSortingRepository<Clothing, Long> {
}
