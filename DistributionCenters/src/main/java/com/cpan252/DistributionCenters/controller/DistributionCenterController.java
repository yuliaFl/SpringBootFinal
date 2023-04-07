package com.cpan252.DistributionCenters.controller;

import com.cpan252.DistributionCenters.model.DistributionCenter;
import com.cpan252.DistributionCenters.repository.CenterRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distribution-centres")
public class DistributionCenterController {
    private final CenterRepository centerRepository;

    public DistributionCenterController(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @GetMapping
    public List<DistributionCenter> getAllDistributionCenters() {
        return (List<DistributionCenter>) centerRepository.findAll();
    }

    @PostMapping
    public DistributionCenter createDistributionCenter(@RequestBody DistributionCenter distributionCenter) {
        return centerRepository.save(distributionCenter);
    }
}

