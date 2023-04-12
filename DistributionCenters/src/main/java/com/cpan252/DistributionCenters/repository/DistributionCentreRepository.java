package com.cpan252.DistributionCenters.repository;

import com.cpan252.DistributionCenters.model.DistributionCenter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistributionCentreRepository extends CrudRepository<DistributionCenter, Long> {
        List<DistributionCenter> findAllByIsWarehouseFalse();
        DistributionCenter findByIsWarehouseTrue();

}
