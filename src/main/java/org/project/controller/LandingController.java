package org.project.controller;

import org.project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String index() {
        System.out.println(dataService.fetchCurrencyData());
        return "Hello World";
    }


}
