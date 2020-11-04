package com.example.virusdb.controller;

import com.example.virusdb.serializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DateController {
    @GetMapping("/date")
    @JsonSerialize(using= JsonDateSerializer.class)
    Date currentDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse("01/01/2001");
    }
}
