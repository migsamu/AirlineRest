package org.iesfm.rest.controllers;

import org.iesfm.rest.Flight;
import org.iesfm.rest.dao.FlightDAO;
import org.iesfm.rest.exceptions.FlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class FlightController {
    private FlightDAO flightDAO;

    public FlightController(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights")
    public List<Flight> list(
            @RequestParam(value = "origin", required = false) String origin
    ) {
        if (origin == null) {
            return flightDAO.list();
        } else {
            return flightDAO.list(origin);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{flightNumber}")
    public Flight getFlight(
            @PathVariable("flightNumber") String flightNumber
    ) {
        try {
            return flightDAO.getFlight(flightNumber);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el vuelo"
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/flights")
    public ResponseEntity<Void> createFlight(@RequestBody Flight flight) {
        if (!flightDAO.addFlight(flight)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existía el vuelo"
            );
        } else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(flight.getFlightNumber()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/flights/{flightNumber}")
    public void updateFlight(
            @PathVariable("flightNumber") String flightNumber,
            @RequestBody Flight flight
    ) {
        try {
            flightDAO.updateFlight(flight);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el vuelo"
            );
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/flights/{flightNumber}")
    public void deleteFlight(
            @PathVariable("flightNumber") String flightNumber
    ) {
        try {
            flightDAO.deleteFlight(flightNumber);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existe el vuelo"
            );
        }
    }

}
