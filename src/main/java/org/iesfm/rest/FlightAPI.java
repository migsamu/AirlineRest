package org.iesfm.rest;

import org.springframework.http.ResponseEntity;


import java.util.List;

public interface FlightAPI {

    List<Flight> list(String origin);

    Flight getFlight(String flightNumber);

    ResponseEntity<Void> createFlight(Flight flight);

    void updateFlight(String flightNumber, Flight flight);
}
