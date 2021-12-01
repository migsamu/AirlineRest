package org.iesfm.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class Flight {
    private String flightNumber;
    private String origin;
    private String destination;

    @JsonCreator
    public Flight(
            @JsonProperty("flightNumber") String flightNumber,
            @JsonProperty("origin") String origin,
            @JsonProperty("destination") String destination) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightNumber, flight.flightNumber) && Objects.equals(origin, flight.origin) && Objects.equals(destination, flight.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, origin, destination);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
