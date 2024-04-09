package com.challenge1.module.controller;

import com.challenge1.module.dto.AddressRequest;
import com.challenge1.module.dto.AddressResponse;
import com.challenge1.module.service.AddressCompletion;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressController {

    private final AddressCompletion addressCompletionService;
    private final AddressCompletion addressCompletionServiceMock;

    public AddressController(AddressCompletion addressCompletionService, AddressCompletion addressCompletionServiceMock){
        this.addressCompletionService = addressCompletionService;
        this.addressCompletionServiceMock = addressCompletionServiceMock;
    }

    @PostMapping("address:autocomplete")
    public List<AddressResponse> addressCompletion(@RequestBody @Valid AddressRequest addressPartial) throws Exception {
        return addressCompletionService.getCompleteAddress(addressPartial);
    }

    @PostMapping("address:autocompleteMock")
    public List<AddressResponse> addressCompletionMock(@RequestBody @Valid AddressRequest addressPartial) throws Exception {
        return addressCompletionServiceMock.getCompleteAddress(addressPartial);
    }
}
