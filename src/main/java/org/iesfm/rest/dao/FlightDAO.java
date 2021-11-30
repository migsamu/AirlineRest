package org.iesfm.rest.dao;

import org.iesfm.rest.Flight;
import org.iesfm.rest.exceptions.FlightNotFoundException;

import java.util.List;

public interface FlightDAO {
    /**
     * Devuelve todos lo vuelos
     *
     * @return
     */
    List<Flight> list();

    /**
     * Devuelve todos los vuelos con un origen
     *
     * @param origin El origen de los vuelos
     * @return
     */
    List<Flight> list(String origin);

    /**
     * Devuelve el vuelo con el número de vuelo proporciondo
     * @param flightNumber
     * @return
     * @throws FlightNotFoundException Se lanza cuando no existe el vuelo
     */
    Flight getFlight(String flightNumber) throws FlightNotFoundException;

    /**
     * Añade un vuelo, si no existía otro vuelo con el mismo flightNumber
     *
     * @param flight El vuelo que se va a añadir
     * @return Si ya había un vuelo con el mismo fligthNumber devuelve false y no se inserta
     */
    boolean addFlight(Flight flight);

    /**
     * Elimina el vuelo con el flightNumber
     * @param flightNumber El flightNumber a eliminar
     * @throws FlightNotFoundException Ocurre cuando no existe el vuelo que se intenta eliminar
     */
    void deleteFlight(String flightNumber) throws FlightNotFoundException;

    /**
     * Actualiza el vuelo
     * @param flight El vuelo a actualizar
     * @throws FlightNotFoundException Ocurre cuando no existe el vuelo que se intenta actualizar
     */
    void updateFlight(Flight flight) throws FlightNotFoundException;
}
