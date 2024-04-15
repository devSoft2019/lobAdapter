package com.challenge1.module.service;

import com.challenge1.module.dto.AddressRequest;
import com.challenge1.module.dto.AddressResponse;
import com.lob.api.client.UsAutocompletionsApi;
import com.lob.model.Suggestions;
import com.lob.model.UsAutocompletions;
import com.lob.model.UsAutocompletionsWritable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class AddressCompletionServiceTest {

    private AddressCompletionService addressCompletionService;
    @Mock
    private UsAutocompletionsApiWrapper usAutocompletionsApiWrapper;
    @Mock
    private UsAutocompletionsApi usAutocompletionsApi;

    @Captor
    private ArgumentCaptor<UsAutocompletionsWritable> captor;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnAListWithCompleteAddressOptions() throws Exception{
        Mockito.when(usAutocompletionsApi
                .autocomplete(Mockito.any())).thenReturn(createUsAutocompletions());
        Mockito.when(usAutocompletionsApiWrapper.getInstance()).thenReturn(usAutocompletionsApi);
        addressCompletionService = new AddressCompletionService(usAutocompletionsApiWrapper);
        AddressRequest addressRequest = new AddressRequest();
        List<AddressResponse> responses = addressCompletionService.getCompleteAddress(addressRequest);
        Mockito.verify(usAutocompletionsApi, Mockito.times(1))
                .autocomplete(Mockito.any());
        Assertions.assertEquals(2, responses.size());
        Assertions.assertEquals("Oak St", responses.stream()
                .findFirst().get().getCompleteAddress());
    }

    @Test
    void shouldSendAddressPrefix() throws Exception{
        Mockito.when(usAutocompletionsApi
                .autocomplete(Mockito.any())).thenReturn(createUsAutocompletions());
        Mockito.when(usAutocompletionsApiWrapper.getInstance()).thenReturn(usAutocompletionsApi);
        addressCompletionService = new AddressCompletionService(usAutocompletionsApiWrapper);
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix("Oak 123");
        addressCompletionService.getCompleteAddress(addressRequest);
        Mockito.verify(usAutocompletionsApi).autocomplete(captor.capture());
        UsAutocompletionsWritable usAutocompletionsWritable = captor.getValue();
        Assertions.assertEquals("Oak 123", usAutocompletionsWritable.getAddressPrefix());
        Assertions.assertEquals(null, usAutocompletionsWritable.getCity());
        Assertions.assertEquals(null, usAutocompletionsWritable.getState());
        Assertions.assertEquals(null, usAutocompletionsWritable.getZipCode());
    }

    @Test
    void shouldSendAddressPrefixAndCity() throws Exception{
        Mockito.when(usAutocompletionsApi
                .autocomplete(Mockito.any())).thenReturn(createUsAutocompletions());
        Mockito.when(usAutocompletionsApiWrapper.getInstance()).thenReturn(usAutocompletionsApi);
        addressCompletionService = new AddressCompletionService(usAutocompletionsApiWrapper);
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix("Oak 123");
        addressRequest.setCity("Miami");
        addressCompletionService.getCompleteAddress(addressRequest);
        Mockito.verify(usAutocompletionsApi).autocomplete(captor.capture());
        UsAutocompletionsWritable usAutocompletionsWritable = captor.getValue();
        Assertions.assertEquals("Oak 123", usAutocompletionsWritable.getAddressPrefix());
        Assertions.assertEquals("Miami", usAutocompletionsWritable.getCity());
        Assertions.assertEquals(null, usAutocompletionsWritable.getState());
        Assertions.assertEquals(null, usAutocompletionsWritable.getZipCode());
    }

    @Test
    void shouldSendAddressPrefixAndCityAndState() throws Exception{
        Mockito.when(usAutocompletionsApi
                .autocomplete(Mockito.any())).thenReturn(createUsAutocompletions());
        Mockito.when(usAutocompletionsApiWrapper.getInstance()).thenReturn(usAutocompletionsApi);
        addressCompletionService = new AddressCompletionService(usAutocompletionsApiWrapper);
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix("Oak 123");
        addressRequest.setCity("Miami");
        addressRequest.setState("FL");
        addressCompletionService.getCompleteAddress(addressRequest);
        Mockito.verify(usAutocompletionsApi).autocomplete(captor.capture());
        UsAutocompletionsWritable usAutocompletionsWritable = captor.getValue();
        Assertions.assertEquals("Oak 123", usAutocompletionsWritable.getAddressPrefix());
        Assertions.assertEquals("Miami", usAutocompletionsWritable.getCity());
        Assertions.assertEquals("FL", usAutocompletionsWritable.getState());
        Assertions.assertEquals(null, usAutocompletionsWritable.getZipCode());
    }

    @Test
    void shouldSendAddressPrefixAndCityAndStateAndZipCode() throws Exception{
        Mockito.when(usAutocompletionsApi
                .autocomplete(Mockito.any())).thenReturn(createUsAutocompletions());
        Mockito.when(usAutocompletionsApiWrapper.getInstance()).thenReturn(usAutocompletionsApi);
        addressCompletionService = new AddressCompletionService(usAutocompletionsApiWrapper);
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix("Oak 123");
        addressRequest.setCity("Miami");
        addressRequest.setState("FL");
        addressRequest.setZipCode("1244");
        addressCompletionService.getCompleteAddress(addressRequest);
        Mockito.verify(usAutocompletionsApi).autocomplete(captor.capture());
        UsAutocompletionsWritable usAutocompletionsWritable = captor.getValue();
        Assertions.assertEquals("Oak 123", usAutocompletionsWritable.getAddressPrefix());
        Assertions.assertEquals("Miami", usAutocompletionsWritable.getCity());
        Assertions.assertEquals("FL", usAutocompletionsWritable.getState());
        Assertions.assertEquals("1244", usAutocompletionsWritable.getZipCode());
    }

    private UsAutocompletions createUsAutocompletions(){
        UsAutocompletions usAutocompletions = new UsAutocompletions();
        String id = "us_auto_ABC123xyz456";
        List<Suggestions> suggestionsList = new ArrayList<>();

        Suggestions suggestion = new Suggestions();
        suggestion.setPrimaryLine("Oak St");
        suggestion.setCity("Los Angeles");
        suggestion.setState("AZ");
        suggestion.setZipCode("80468");
        suggestionsList.add(suggestion);

        suggestion = new Suggestions();
        suggestion.setPrimaryLine("Main St");
        suggestion.setCity("Chicago");
        suggestion.setState("CA");
        suggestion.setZipCode("75641");
        suggestionsList.add(suggestion);

        usAutocompletions.setSuggestions(suggestionsList);
        usAutocompletions.setId(id);
        return usAutocompletions;
    }

}