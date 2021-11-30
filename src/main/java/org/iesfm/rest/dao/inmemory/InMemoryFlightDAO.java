package org.iesfm.rest.dao.inmemory;

import org.iesfm.rest.Flight;
import org.iesfm.rest.dao.FlightDAO;
import org.iesfm.rest.exceptions.FlightNotFoundException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryFlightDAO implements FlightDAO {

    private Map<String, Flight> flights = new HashMap<>();

    @Override
    public List<Flight> list() {
        return new LinkedList<>(flights.values());
    }

    @Override
    public List<Flight> list(String origin) {
        return list().stream().filter(flight -> flight.getOrigin().equals(origin)).collect(Collectors.toList());
    }

    @Override
    public Flight getFlight(String flightNumber) throws FlightNotFoundException {
        if(flights.containsKey(flightNumber)) {
            return flights.get(flightNumber);
        } else {
            throw new FlightNotFoundException();
        }
    }

    @Override
    public boolean addFlight(Flight flight) {
        if (flights.containsKey(flight.getFlightNumber())) {
            return false;
        } else {
            flights.put(flight.getFlightNumber(), flight);
            return true;
        }
    }

    @Override
    public void deleteFlight(String flightNumber) throws FlightNotFoundException {
        if (flights.remove(flightNumber) == null) {
            throw new FlightNotFoundException();
        }
    }

    @Override
    public void updateFlight(Flight flight) throws FlightNotFoundException {
        if (!flights.containsKey(flight.getFlightNumber())) {
            throw new FlightNotFoundException();
        } else {
            flights.put(flight.getFlightNumber(), flight);
        }
    }
}
