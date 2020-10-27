package com.example.virusdb.service;

import com.example.virusdb.model.Human;
import com.example.virusdb.model.Virus;
import com.example.virusdb.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class HumanService implements IHumanService{
    @Autowired
    private HumanRepository repository;
    @Override
    public Page<Human> findPaginated(int page, int size) {
        Pageable paging = PageRequest.of(page,size);
        return repository.findAll(paging);
    }

    @Override
    public void clearAndCreateMillionHumans() {
        repository.deleteAll();
        repository.insertMillionHumans();
    }

    @Override
    public void infect(int numberOfHumans) {

    }
}
