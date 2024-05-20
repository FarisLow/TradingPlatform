package org.project.service;

import org.project.model.CurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DataService {

    @Autowired
    private WebClient webClient;

    @Value("${client.api.key}")
    private String apiKey;

    @Value("${client.api.currencies}")
    private String requestUrl;
    public CurrencyResponse fetchCurrencyData() {
        try {
            return webClient.get()
                    .uri(requestUrl)
                    .header("apikey", apiKey)
                    .retrieve()
                    .bodyToMono(CurrencyResponse.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace of the exception
            return null; // Or handle the error in a way appropriate for your application
        }
    }
}
