package com.example.virusdb.controller;

import com.example.virusdb.model.Human;
import com.example.virusdb.model.Virus;
import com.example.virusdb.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HumanController {
    @Autowired
    private IHumanService humanService;

    @GetMapping("/humans")
    public Page<Human> getHumans(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "0") int size) {
        size = (page == 0 && size == 0) ? Integer.MAX_VALUE : size;
        return humanService.findPaginated(page, size);
    }

    @PostMapping(value = "/humans/addMillion")
    void addBillion() {
        humanService.clearAndCreateMillionHumans();
    }
}
