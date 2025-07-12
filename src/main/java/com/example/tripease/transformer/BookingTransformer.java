package com.example.tripease.transformer;

import com.example.tripease.Enum.TripStatus;
import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Cab;
import com.example.tripease.model.Customer;
import com.example.tripease.model.Driver;

public class BookingTransformer {
    public static Booking bookingRequestToBooking(BookingRequest bookingRequest,double perKmRate){
        return Booking.builder()
                .pikup(bookingRequest.getPikup())
                .destination(bookingRequest.getDestination())
                .tripDistanceInKm(bookingRequest.getTripDistanceInKm())
                .tripStatus(TripStatus.IN_PROGRESS)
                .billAmount(bookingRequest.getTripDistanceInKm()*perKmRate)
                .build();

    }


    public static BookingResponse bookingToBookingResponse(Booking booking,
                                                           Customer customer,
                                                           Cab cab,
                                                           Driver driver){
        return BookingResponse.builder()
                .pikup(booking.getPikup())
                .destination(booking.getDestination())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getBillAmount())
                .bookedate(booking.getBookedate())
                .lastUpdateAt(booking.getLastUpdateAt())
                .customer(CustomerTransformer.customerTocustomerResponse(customer))
                .cab(CabTransformer.cabToCabResponse(cab,driver))
                .build();
    }
}
