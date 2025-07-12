package com.example.tripease.controller;

import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.service.DriverSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverConroller {
    @Autowired
    DriverSerice driverSerice;

    @PostMapping("/add")
    public DriverResponse addDriver(@RequestBody DriverRequest driverRequest){
        return driverSerice.addDriver(driverRequest);
    }

    @GetMapping("/get-driver/{id}")
    public DriverResponse getDriver(@PathVariable("id") int driverId ){
        return driverSerice.getDriver(driverId);
    }

    @GetMapping("/get/age")
    public List<DriverResponse> getDriverAge(@RequestParam("age") int age ){
        return driverSerice.getDriverAge(age);
    }

}
