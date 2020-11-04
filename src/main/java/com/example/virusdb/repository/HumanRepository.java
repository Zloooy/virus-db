package com.example.virusdb.repository;

import com.example.virusdb.model.Human;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;


@Repository
public interface HumanRepository extends PagingAndSortingRepository<Human, Long> {
    @Transactional(isolation = Isolation.SERIALIZABLE, timeout=120)
    @Modifying
    @Query(value = "INSERT INTO humans SELECT nextval('human_sequence') FROM generate_series(1,1000000)", nativeQuery = true)
    void insertMillionHumans();
    @Transactional(isolation = Isolation.SERIALIZABLE, timeout=60)
    @Modifying
    @Query(value = "INSERT INTO injuries(id, human_id, virus_id, infection_date, virus_stage) SELECT nextval('injury_sequence'), id, :virus_id, :infection_date, 'ATTACHED' FROM humans TABLESAMPLE SYSTEM_ROWS(:human_number)", nativeQuery = true)
    void infectHumans(
            @Param("virus_id") long virusId,
            @Param("human_number")int humanNumber,
            @Param("infection_date") Date infectionDate
    );
}
