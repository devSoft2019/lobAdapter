package com.challenge1.module.service;

import com.challenge1.module.dto.AddressRequest;
import com.challenge1.module.dto.AddressResponse;
import com.lob.api.ApiException;
import com.lob.model.UsAutocompletions;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AddressCompletion {

    public abstract List<AddressResponse> getCompleteAddress(final AddressRequest addressRequest)
            throws ApiException;

    public List<AddressResponse> transformToAddressList(final UsAutocompletions usAutocompletions) {
        return usAutocompletions
                .getSuggestions()
                .stream()
                .map(s ->
                        AddressResponse
                                .builder()
                                .completeAddress(s.getPrimaryLine())
                                .city(s.getCity())
                                .state(s.getState())
                                .zipCode(s.getZipCode())
                                .build())
                .collect(Collectors.toList());
    }
}
