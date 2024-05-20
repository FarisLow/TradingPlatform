package org.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class WebClientService {

    @Value("${client.api.url}")
    String baseUrl;

    @Value("${client.api.key}")
    String apiKey;

    public String getApiUrl() {
        return baseUrl + "?apikey=" + apiKey;
    }


}
