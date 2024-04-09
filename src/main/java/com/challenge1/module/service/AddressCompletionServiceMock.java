package com.challenge1.module.service;

import com.challenge1.module.dto.AddressRequest;
import com.challenge1.module.dto.AddressResponse;
import com.lob.api.ApiException;
import com.lob.model.Suggestions;
import com.lob.model.UsAutocompletions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("addressCompletionServiceMock")
public class AddressCompletionServiceMock extends AddressCompletion {

    @Override
    public List<AddressResponse> getCompleteAddress(AddressRequest addressRequest) throws ApiException {
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

        suggestion = new Suggestions();
        suggestion.setPrimaryLine("Cedar St");
        suggestion.setCity("Houston");
        suggestion.setState("AZ");
        suggestion.setZipCode("77360");
        suggestionsList.add(suggestion);

        suggestion = new Suggestions();
        suggestion.setPrimaryLine("Maple Ave");
        suggestion.setCity("Houston");
        suggestion.setState("AZ");
        suggestion.setZipCode("37812");
        suggestionsList.add(suggestion);

        suggestion = new Suggestions();
        suggestion.setPrimaryLine("Elm St");
        suggestion.setCity("Chicago");
        suggestion.setState("AZ");
        suggestion.setZipCode("01385");
        suggestionsList.add(suggestion);

        usAutocompletions.setSuggestions(suggestionsList);
        usAutocompletions.setId(id);
        return super.transformToAddressList(usAutocompletions);
    }
}
