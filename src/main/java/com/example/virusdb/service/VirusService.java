package com.example.virusdb.service;

import com.example.virusdb.model.Virus;
import com.example.virusdb.repository.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VirusService implements IVirusService{
    @Autowired
    private VirusRepository repository;

    @Override
    public Page<Virus> findPaginated(int page, int size) {
        Pageable paging = PageRequest.of(page,size, Sort.by("name"));
        return repository.findAll(paging);
    }

    @Override
    public List<Virus> getAllViruses() {
        ArrayList<Virus> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<Map.Entry<Long, String>> getVirusNames() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map((Virus v)->new AbstractMap.SimpleEntry<>(v.getId(), v.getName()))
        .collect(Collectors.toList());
    }

    @Override
    public Long addVirus(Virus virus) {
        return repository.save(virus).getId();

    }

    @Override
    public boolean deleteVirus(Long id) {
        boolean result = repository.existsById(id);
        if (result)
            repository.deleteById(id);
        return result;
    }

    @Override
    public boolean updateVirus(Long id, Virus virus) {
        boolean result = repository.existsById(id);
        if (result)
        {
            virus.setId(id);
            repository.save(virus);
        }
        System.out.println("exists "+result);
        return result;
    }
}
