package com.example.virusdb.controller;

import com.example.virusdb.service.IInfectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class InfectionController {
    @Autowired
    IInfectionService infectionService;
    @Autowired
    DateController dateController;
    @PostMapping(value="/infect")
    Map<String, Boolean> infect(@RequestParam Long virusId, @RequestParam int humanNumber) throws ParseException {
        return Map.of(
            "infected", infectionService.infect(virusId, humanNumber, dateController.currentDate())
        );
    }
}
