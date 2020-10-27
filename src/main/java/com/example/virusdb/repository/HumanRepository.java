package com.example.virusdb.repository;

import com.example.virusdb.model.Human;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface HumanRepository extends PagingAndSortingRepository<Human, Long> {
    @Transactional(isolation = Isolation.SERIALIZABLE, timeout=120)
    @Modifying
    @Query(value = "INSERT INTO humans SELECT nextval('human_sequence') FROM generate_series(1,1000000)", nativeQuery = true)
    void insertMillionHumans();
}
