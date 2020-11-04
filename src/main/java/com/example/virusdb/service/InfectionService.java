package com.example.virusdb.service;

import com.example.virusdb.repository.HumanRepository;
import com.example.virusdb.repository.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InfectionService implements IInfectionService{
    @Autowired
    HumanRepository humanRepository;
    @Autowired
    VirusRepository virusRepository;
    @Override
    public boolean infect(long virusId, int humanNumber, Date infectionDate) {
        if (virusRepository.existsById(virusId)) {
            humanRepository.infectHumans(virusId, humanNumber, infectionDate);
            return true;
        }
        return false;
    }
}
