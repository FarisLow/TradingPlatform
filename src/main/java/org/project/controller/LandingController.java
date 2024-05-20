package org.project.controller;

import org.project.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

    @Autowired
    private WebClientService webClientService;

    @GetMapping("/")
    public String index() {
        return webClientService.getApiUrl();
    }


}
