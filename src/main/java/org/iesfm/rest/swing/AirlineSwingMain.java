package org.iesfm.rest.swing;

import org.iesfm.rest.Flight;
import org.iesfm.rest.clients.FlightClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.*;
import java.util.List;

public class AirlineSwingMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aerolínea");
        JPanel panel = new JPanel();

        FlightClient flightAPI = new FlightClient(new RestTemplateBuilder().rootUri("http://localhost:8080").build());

        List<Flight> flights = flightAPI.list(null);
        for (Flight flight : flights) {
            panel.add(new JLabel(flight.toString()));
        }
        try {
            Flight flight = flightAPI.getFlight("no existe");
        } catch (HttpClientErrorException.NotFound e) {
            JOptionPane.showMessageDialog(frame, "No se encuentra el vuelo");
        }

        frame.add(panel);
        frame.setVisible(true);
        frame.setBounds(0, 0, 600, 600);
    }
}
