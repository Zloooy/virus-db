package com.example.virusdb.repository;

import com.example.virusdb.model.Virus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VirusRepository extends PagingAndSortingRepository<Virus,Long> {
}
