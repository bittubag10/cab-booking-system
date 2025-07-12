package com.example.tripease.service;

import com.example.tripease.Enum.Gender;
import com.example.tripease.dto.request.CustomerRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.exception.CustomerNotFoundException;
import com.example.tripease.model.Customer;
import com.example.tripease.repository.CustomerRepository;
import com.example.tripease.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        //REQUEST DTO=> ENTITY
       Customer customer= CustomerTransformer.customerRequestToCustomer(customerRequest);

        //SAVE ENTITY TO -> DB
        Customer savedCustomer= customerRepository.save(customer);

        // SAVE ENTITY TO --> DTO
        return CustomerTransformer.customerTocustomerResponse(customer);
    }


    public CustomerResponse getCustomer(int customerId) {
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid customer id");
        }
        Customer savedCustomer = optionalCustomer.get();

        // SAVED ENTITY  --> RESPONSE DTO
        return CustomerTransformer.customerTocustomerResponse(savedCustomer);

    }


    public List<CustomerResponse> getAllByGender(Gender gender) {
        List<Customer> customers=customerRepository.findByGender(gender);
        // ENTITY TO  ----> response dto
        List<CustomerResponse> customerResponses= new ArrayList<>();
        for(Customer customer : customers){
            customerResponses.add(CustomerTransformer.customerTocustomerResponse(customer));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAge(Gender gender, int age) {
        List<Customer> customers=customerRepository.getAllByGenderAndAge(gender,age);
        // convart ENTITY to --------> response dto

        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer : customers){
            customerResponses.add(CustomerTransformer.customerTocustomerResponse(customer));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAgeGreaterThen(String gender, int age) {
        List<Customer> customers=customerRepository.getAllByGenderAndAgeGreaterThen(gender,age);
        // convart ENTITY to --------> response dto

        List<CustomerResponse> customerResponses=new ArrayList<>();
        for(Customer customer : customers){
            customerResponses.add(CustomerTransformer.customerTocustomerResponse(customer));
        }
        return customerResponses;
    }
}
