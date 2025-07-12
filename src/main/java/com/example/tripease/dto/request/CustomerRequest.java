package com.example.tripease.dto.request;

import com.example.tripease.Enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {

    private String name;

    private int age;

    private String emailId;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}
