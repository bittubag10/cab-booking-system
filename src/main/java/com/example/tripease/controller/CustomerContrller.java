package com.example.tripease.controller;

import com.example.tripease.Enum.Gender;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.model.Customer;
import com.example.tripease.service.CustomerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerContrller {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.addCustomer(customerRequest);
    }


    @GetMapping("/get/customer-id/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") int customerId){
        return customerService.getCustomer(customerId);
    }


    @GetMapping("/get/gender/{gender}")
    public List<CustomerResponse> getAllByGender(@PathVariable("gender") Gender gender){
        return customerService.getAllByGender(gender);
    }


    // get all people of a particular gender and age

    @GetMapping("/get")
    public List<CustomerResponse> getAllByGenderAndAge (@RequestParam("gender")Gender gender,
                                                        @RequestParam("age")int age){

        return customerService.getAllByGenderAndAge(gender,age);
    }

    @GetMapping("/get-by-age-greater-then")
    public List<CustomerResponse> getAllByGenderAndAgeGreaterThen (@RequestParam("gender")String gender,
                                                        @RequestParam("age")int age){

        return customerService.getAllByGenderAndAgeGreaterThen(gender,age);
    }

}
