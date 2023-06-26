package com.company;

import java.time.LocalDateTime;

public class Flight {
    private String origin;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double duration;
    private double price;
    private int flightNumber;

    public Flight(String origin, String destination, LocalDateTime departure, LocalDateTime arrival, double duration, double price, int flightNumber) {
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
        this.price = price;
        this.flightNumber = flightNumber;
    }
}
