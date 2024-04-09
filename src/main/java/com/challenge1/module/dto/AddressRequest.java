package com.challenge1.module.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressRequest {

    @NotNull(message = "Address prefix is required")
    private String addressPrefix;
    private String city;
    private String state;
    private String zipCode;
}
