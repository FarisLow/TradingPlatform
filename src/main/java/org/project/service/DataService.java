package org.project.service;

import org.project.model.Currencies;
import org.project.model.CurrencyResponse;
import org.project.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    private WebClient webClient;

    @Value("${client.api.key}")
    private String apiKey;
    @Value("${client.api.currencies}")
    private String allCurrencies;

    @Autowired
    private CurrencyRepository currencyRepository;


    //@Cacheable(value = "currencyResponseCache")
    public CurrencyResponse fetchCurrencyData() {
        //try to find in DB first
        if(!currencyRepository.findAll().isEmpty()){
             List<Currencies> currencies = currencyRepository.findAll();
            System.out.println("Data retrieve from DB");
            CurrencyResponse response = new CurrencyResponse();
            response.setData(listToMap(currencies));
            return response;
        } else {
            System.out.println("Data retrieve from API, not cache");
            try {
                CurrencyResponse response = webClient.get()
                        .uri(allCurrencies)
                        .header("apikey", apiKey)
                        .retrieve()
                        .bodyToMono(CurrencyResponse.class)
                        .block();

                if (response == null) {
                    System.out.println("Could not retrieve response from API");
                    return null;
                }
                saveCurrencyResponse(response);
                return response;


            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace of the exception
                return null; // Or handle the error in a way appropriate for your application
            }
        }
    }

    public void saveCurrencyResponse(CurrencyResponse currencyResponse) {
        currencyResponse.getData().forEach((key, currencies) -> currencies.setTimestamp(new Timestamp(System.currentTimeMillis())));
        currencyResponse.getData().forEach((key, currencies) -> currencyRepository.save(currencies));
        System.out.println("Data save to DB");
    }

    public Map<String, Currencies> listToMap(List<Currencies> list) {
        Map<String, Currencies> map = new HashMap<>();
        for (Currencies currency : list) {
            map.put(currency.getSymbol(), currency);
        }
        return map;
    }
}
