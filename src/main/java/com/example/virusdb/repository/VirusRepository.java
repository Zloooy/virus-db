package com.example.virusdb.repository;

import com.example.virusdb.model.Virus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface VirusRepository extends PagingAndSortingRepository<Virus,Long> {
}
