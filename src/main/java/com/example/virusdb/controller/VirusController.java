package com.example.virusdb.controller;

import com.example.virusdb.model.Virus;
import com.example.virusdb.service.IVirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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