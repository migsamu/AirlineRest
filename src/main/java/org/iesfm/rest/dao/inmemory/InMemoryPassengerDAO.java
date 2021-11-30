package org.iesfm.rest.dao.inmemory;

import org.iesfm.rest.Passenger;
import org.iesfm.rest.dao.PassengerDAO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryPassengerDAO implements PassengerDAO {
    private Map<String, Map<String, Passenger>> passengersByFlight = new HashMap<>();

    @Override
    public List<Passenger> getFlightPassengers(String flightNumber) {
        if (passengersByFlight.containsKey(flightNumber)) {
            return new LinkedList<>(passengersByFlight.get(flightNumber).values());
        } else {
            return new LinkedList<>();
        }
    }

    @Override
    public Passenger getFlightPassenger(String flightNumber, String nif) {
        if (passengersByFlight.containsKey(flightNumber)) {
            return passengersByFlight.get(flightNumber).get(nif);
        } else {
            return null;
        }
    }

    @Override
    public boolean addPassenger(String flightNumber, Passenger passenger) {
        if (passengersByFlight.containsKey(flightNumber)) {
            Map<String, Passenger> passengers = passengersByFlight.get(flightNumber);
            if (passengers.containsKey(passenger.getNif())) {
                return false;
            } else {
                passengers.put(passenger.getNif(), passenger);
                return true;
            }
        } else {
            Map<String, Passenger> passengers = new HashMap<>();
            passengers.put(passenger.getNif(), passenger);
            passengersByFlight.put(flightNumber, passengers);
            return true;
        }
    }

    @Override
    public boolean deletePassenger(String flightNumber, String nif) {
        if (passengersByFlight.containsKey(flightNumber)) {
            return passengersByFlight.get(flightNumber).remove(nif) != null;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePassenger(String flightNumber, Passenger passenger) {
        if (passengersByFlight.containsKey(flightNumber)) {
            if (passengersByFlight.get(flightNumber).containsKey(passenger.getNif())) {
                passengersByFlight.get(flightNumber).put(passenger.getNif(), passenger);
                return true;
            }
        }
        return false;

    }
}
