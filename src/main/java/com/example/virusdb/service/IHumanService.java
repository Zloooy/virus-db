package com.example.virusdb.service;

import com.example.virusdb.model.Human;
import com.example.virusdb.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IHumanService {
    Page<Human> findPaginated(int page, int size);
    void clearAndCreateMillionHumans();
    void infect(int numberOfHumans);
}
