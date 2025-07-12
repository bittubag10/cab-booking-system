package com.example.tripease.service;

import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.exception.CabUnavalableException;
import com.example.tripease.exception.CustomerNotFoundException;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Cab;
import com.example.tripease.model.Customer;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.BookingRepository;
import com.example.tripease.repository.CabRepository;
import com.example.tripease.repository.CustomerRepository;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    CustomerRepository customerRepository;

   @Autowired
    CabRepository cabRepository;

   @Autowired
   DriverRepository driverRepository;

   @Autowired
   BookingRepository bookingRepository;

   @Autowired
    JavaMailSender javaMailSender;


    public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer id is invalid");
        }
        Customer customer=optionalCustomer.get();

        Cab avalableCab=cabRepository.getAvailableCabRandomly();

        if(avalableCab==null){
            throw new CabUnavalableException("Sorry noy cab avalable");
        }

        Booking booking = BookingTransformer.bookingRequestToBooking(bookingRequest,avalableCab.getPerKmRate());
        Booking savedbooking=bookingRepository.save(booking);

        avalableCab.setAvailable(false);
        customer.getBookings().add(savedbooking);

        Driver driver=driverRepository.getDriverByCabId(avalableCab.getCabId());
        driver.getBookings().add(savedbooking);

        Customer savedCustomer=customerRepository.save(customer);
        Driver savrDriver=driverRepository.save(driver);
        sendEmail(savedCustomer);

        return BookingTransformer.bookingToBookingResponse(savedbooking,savedCustomer,avalableCab,savrDriver);

    }
    private void sendEmail(Customer customer){
        String text="Congrats !! "+customer.getName()+" your cab successfully booked";
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("bagbittu816@gmail.com");
        simpleMailMessage.setTo(customer.getEmailId());
        simpleMailMessage.setSubject(" Cab Booked ! ");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }


}
