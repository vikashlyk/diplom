package com.example.bntu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtractParamsDto {
    String extractNumber;
    String educationTerm;
    String date;
    String registrationNumber;
}
