package com.example.tripease.model;

import com.example.tripease.Enum.TripStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookingId;

    String pikup;

    String destination;

    double tripDistanceInKm;

    TripStatus tripStatus;

    double billAmount;

    @CreationTimestamp
    Date bookedate;

    @UpdateTimestamp
    Date lastUpdateAt;


}
