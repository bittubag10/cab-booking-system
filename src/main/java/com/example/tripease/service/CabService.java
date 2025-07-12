package com.example.tripease.service;

import com.example.tripease.dto.request.CabRequest;
import com.example.tripease.dto.response.CabResponse;
import com.example.tripease.exception.DriverNotFoundException;
import com.example.tripease.model.Cab;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.CabRepository;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabService {
    @Autowired
    DriverRepository driverRepository;

    public CabResponse registerCab(CabRequest cabRequest, int driverId) {

        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()){
            throw  new DriverNotFoundException("Invalid Driver Id");
        }
        Driver driver=optionalDriver.get();


        Cab cab = CabTransformer.cabRequestToCab(cabRequest);

        driver.setCab(cab);

        Driver savedDriver= driverRepository.save(driver); // saved both cab and driver

        return CabTransformer.cabToCabResponse(savedDriver.getCab(),savedDriver);
    }
}
