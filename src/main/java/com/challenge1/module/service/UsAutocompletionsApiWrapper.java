package com.challenge1.module.service;

import com.lob.api.ApiClient;
import com.lob.api.Configuration;
import com.lob.api.auth.HttpBasicAuth;
import com.lob.api.client.UsAutocompletionsApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UsAutocompletionsApiWrapper {

    @Value("${lob.apiKey}")
    private String apiKey;
    private UsAutocompletionsApi usAutocompletionsApi;

    @PostConstruct
    private void init(){
        ApiClient lobClient = Configuration.getDefaultApiClient();
        HttpBasicAuth basicAuth = (HttpBasicAuth) lobClient.getAuthentication("basicAuth");
        basicAuth.setUsername(apiKey);
        usAutocompletionsApi = new  UsAutocompletionsApi(lobClient);
    }

    public UsAutocompletionsApi getInstance(){
      return usAutocompletionsApi;
    }
}
