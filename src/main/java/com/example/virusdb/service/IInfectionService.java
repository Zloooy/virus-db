package com.example.virusdb.service;

import java.util.Date;

public interface IInfectionService {

    boolean infect(long virusId, int humanNumber, Date infectionDate);
}
