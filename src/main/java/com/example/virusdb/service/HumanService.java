package com.example.virusdb.service;

import com.example.virusdb.model.Human;
import com.example.virusdb.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Service
public class HumanService implements IHumanService{
    @PostConstruct
    void init() throws SQLException {
        DataSource ds = (DataSource) context.getBean("dataSource");
        Connection con = ds.getConnection();
        con.nativeSQL("CREATE EXTENSION IF NOT EXISTS tsm_system_rows;");
        //con.commit();
    }
    @Autowired
    private ApplicationContext context;
    @Autowired
    private HumanRepository repository;
    @Override
    public Page<Human> findPaginated(int page, int size) {
        Pageable paging = PageRequest.of(page,size, Sort.by("id"));
        return repository.findAll(paging);
    }

    @Override
    public void clearAndCreateMillionHumans() {
        repository.deleteAll();
        repository.insertMillionHumans();
    }
}
