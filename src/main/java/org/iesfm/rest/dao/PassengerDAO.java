package org.iesfm.rest.dao;

import org.iesfm.rest.Passenger;

import java.util.List;

public interface PassengerDAO {

    /**
     * Devuelve todos los pasajeros de un vuelo
     * @param flightNumber El número del vuelo
     * @return
     */
    List<Passenger> getFlightPassengers(String flightNumber);

    /**
     * Devuelve los datos de un pasajero en un vuelo
     * @param flightNumber El número de vuelo
     * @param nif El nif del pasajero
     * @return Devuelve null si no existe el pasajero
     */
    Passenger getFlightPassenger(String flightNumber, String nif);

    /**
     * Añade un pasajero a un vuelo
     * @param flightNumber El número de vuelo
     * @param passenger El pasajero que se va a añadir
     * @return Devuelve false si el pasajero ya estaba registrado en el vuelo
     */
    boolean addPassenger(String flightNumber, Passenger passenger);

    /**
     * Elimina un pasajero de un vuelo
     * @param flightNumber El número de vuelo
     * @param nif El nif del pasajero
     * @return Devuelve false si el pasajero no estaba registrado en el vuelo
     */
    boolean deletePassenger(String flightNumber, String nif);

    /**
     * Actualiza los datos de un pasajero de un vuelo
     * @param flightNumber El número de vuelo
     * @param passenger El pasajero
     * @return Devuelve false si el pasajero no estaba registrado en el vuelo
     */
    boolean updatePassenger(String flightNumber, Passenger passenger);
}
