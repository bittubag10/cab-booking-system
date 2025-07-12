package com.example.tripease.service;

import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.exception.DriverNotFoundException;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverSerice {
    @Autowired
    DriverRepository driverRepository;


    public DriverResponse addDriver(DriverRequest driverRequest) {
        Driver driver= DriverTransformer.driverRequestoDriver(driverRequest);

        Driver saveDriver = driverRepository.save(driver);

        return DriverTransformer.driverResponsetodriver(driver);
    }

    public DriverResponse getDriver(int driverId) {
        Optional<Driver> optionalDriver=driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()){
            throw new DriverNotFoundException("Invalid Driver Id");
        }
        Driver savedDriver=optionalDriver.get();

        return DriverTransformer.driverResponsetodriver(savedDriver);
    }


    public List<DriverResponse> getDriverAge(int age) {
        List<Driver> drivers=driverRepository.findByAge(age);

        List<DriverResponse> driverResponses=new ArrayList<>();

        for(Driver driver : drivers){
            driverResponses.add(DriverTransformer.driverResponsetodriver(driver));
        }
        return driverResponses;
    }
}
