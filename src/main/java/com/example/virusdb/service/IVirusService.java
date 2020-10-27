package com.example.virusdb.service;

import com.example.virusdb.model.Virus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface IVirusService {
    Page<Virus> findPaginated(int page, int size);
    Long addVirus(Virus virus);
    boolean deleteVirus(Long id);

    boolean updateVirus(Long id, Virus virus);
}
