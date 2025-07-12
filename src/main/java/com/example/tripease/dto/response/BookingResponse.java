package com.example.tripease.dto.response;

import com.example.tripease.Enum.TripStatus;
import lombok.*;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {
    String pikup;

    String destination;

    double tripDistanceInKm;

    TripStatus tripStatus;

    double billAmount;


    Date bookedate;


    Date lastUpdateAt;


    CustomerResponse customer;


    CabResponse cab;
}
