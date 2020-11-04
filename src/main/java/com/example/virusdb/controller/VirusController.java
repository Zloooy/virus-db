package com.example.virusdb.controller;

import com.example.virusdb.model.Virus;
import com.example.virusdb.service.IVirusService;
import com.example.virusdb.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class VirusController {

    @Autowired
    private IVirusService virusService;

    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        System.out.println("returning options");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/viruses")
    public Page<Virus> getViruses(@RequestParam(required = false,defaultValue = "0") int page, @RequestParam(required = false,defaultValue = "0") int size)
    {
        size = (page == 0 && size == 0) ? Integer.MAX_VALUE : size;
        return virusService.findPaginated(page,size);
    }
    @JsonView(View.ShortVirus.class)
    @GetMapping("/viruses/names")
    public List<Virus> getVirusNames(){
        return virusService.getAllViruses();
        //return virusService.getVirusNames().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @PostMapping(value = "/viruses/add", consumes = "application/json")
    Map<String,Long> addVirus(@RequestBody Virus virus){
        return Map.of(
            "id", virusService.addVirus(virus)
        );
    }

    @DeleteMapping("/viruses/{id}")
    Map<String,Boolean> removeVirus(@PathVariable(name="id") Long id)
    {
        return Map.of(
                "removed", virusService.deleteVirus(id)
        );
    }

    @PutMapping(value = "/viruses/{id}", consumes ="application/json")
    Map<String,Boolean> patchVirus(@PathVariable(name="id") Long id, @RequestBody Virus virus)
    {
        return Map.of("put",virusService.updateVirus(id, virus));
    }
}