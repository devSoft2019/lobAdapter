package com.challenge1.module.service;

import com.challenge1.module.dto.AddressRequest;
import com.challenge1.module.dto.AddressResponse;
import com.lob.api.ApiClient;
import com.lob.api.ApiException;
import com.lob.api.Configuration;
import com.lob.api.auth.HttpBasicAuth;
import com.lob.api.client.UsAutocompletionsApi;
import com.lob.model.UsAutocompletions;
import com.lob.model.UsAutocompletionsWritable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("addressCompletionService")
public class AddressCompletionService extends AddressCompletion {

    @Value("${lob.apiKey}")
    private String apiKey;

    public List<AddressResponse> getCompleteAddress(final AddressRequest addressRequest) throws ApiException {
        ApiClient lobClient = Configuration.getDefaultApiClient();
        HttpBasicAuth basicAuth = (HttpBasicAuth) lobClient.getAuthentication("basicAuth");
        basicAuth.setUsername(apiKey);
        UsAutocompletionsApi apiInstance = new UsAutocompletionsApi(lobClient);
        UsAutocompletionsWritable autoCompletionWritable = new UsAutocompletionsWritable();
        autoCompletionWritable.setAddressPrefix(addressRequest.getAddressPrefix());
        Optional.ofNullable(addressRequest.getCity()).ifPresent(autoCompletionWritable::setCity);
        Optional.ofNullable(addressRequest.getState()).ifPresent(autoCompletionWritable::setState);
        Optional.ofNullable(addressRequest.getZipCode()).ifPresent(autoCompletionWritable::setZipCode);
        UsAutocompletions usAutocompletion = apiInstance.autocomplete(autoCompletionWritable);
        return super.transformToAddressList(usAutocompletion);
    }
}
