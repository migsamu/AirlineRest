package org.iesfm.rest.dao;

import org.iesfm.rest.dao.inmemory.InMemoryFlightDAO;
import org.iesfm.rest.dao.inmemory.InMemoryPassengerDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOConfiguration {

    @Bean
    public FlightDAO flightDAO() {
        return new InMemoryFlightDAO();
    }

    @Bean
    public PassengerDAO passengerDAO() {
        return new InMemoryPassengerDAO();
    }
}
