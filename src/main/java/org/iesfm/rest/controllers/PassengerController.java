package org.iesfm.rest.controllers;

import org.iesfm.rest.Flight;
import org.iesfm.rest.Passenger;
import org.iesfm.rest.dao.FlightDAO;
import org.iesfm.rest.dao.PassengerDAO;
import org.iesfm.rest.exceptions.FlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PassengerController {

    private FlightDAO flightDAO;
    private PassengerDAO passengerDAO;

    public PassengerController(FlightDAO flightDAO, PassengerDAO passengerDAO) {
        this.flightDAO = flightDAO;
        this.passengerDAO = passengerDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flightNumber}/passengers")
    public List<Passenger> list(
            @PathVariable("flightNumber") String flightNumber
    ) {
        try {
            flightDAO.getFlight(flightNumber);
            return passengerDAO.getFlightPassengers(flightNumber);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el vuelo"
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/flights/{flightNumber}/passengers     ")
    public ResponseEntity<Void> createFlight(
            @PathVariable("flightNumber") String flightNumber,
            @RequestBody Passenger passenger) {
        try {
            flightDAO.getFlight(flightNumber);
            if (passengerDAO.addPassenger(flightNumber, passenger)) {
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(passenger.getNif()).toUri();
                return ResponseEntity.created(location).build();
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (FlightNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flightNumber}/passengers/{nif}")
    public Passenger getPassenger(
            @PathVariable("flightNumber") String flightNumber,
            @PathVariable("nif") String nif) {
        try {
            flightDAO.getFlight(flightNumber);
            Passenger passenger = passengerDAO.getFlightPassenger(flightNumber, nif);
            if (passenger == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el pasajero"
                );
            } else {
                return passenger;
            }
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el vuelo"
            );
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/flights/{flightNumber}/passengers/{nif}")
    public void deletePassenger(
            @PathVariable("flightNumber") String flightNumber,
            @PathVariable("nif") String nif) {
        try {
            flightDAO.getFlight(flightNumber);

            if (!passengerDAO.deletePassenger(flightNumber, nif)) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el pasajero"
                );
            }
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el vuelo"
            );
        }
    }
}
