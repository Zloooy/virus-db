package com.example.virusdb.service;

import com.example.virusdb.model.Virus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface IVirusService {
    Page<Virus> findPaginated(int page, int size);
    List<Virus> getAllViruses();
    List<Map.Entry<Long, String>> getVirusNames();
    Long addVirus(Virus virus);
    boolean deleteVirus(Long id);
    boolean updateVirus(Long id, Virus virus);
}
