package com.challenge1.module.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private String completeAddress;
    private String city;
    private String state;
    private String zipCode;
}
