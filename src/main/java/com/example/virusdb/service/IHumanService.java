package com.example.virusdb.service;

import com.example.virusdb.model.Human;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IHumanService {
    Page<Human> findPaginated(int page, int size);
    void clearAndCreateMillionHumans();
}
