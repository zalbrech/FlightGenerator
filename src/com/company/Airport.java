package com.company;

import java.util.HashMap;
import java.util.Map;

public class Airport {
    String id;
    String name;
    String city;
    double latitude;
    double longitude;
    Map<String, Integer> flightTimes;

    public Airport(String id, String name, String city, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        flightTimes = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id = '" + id + '\'' +
                ", name = '" + name + '\'' +
                ", city = '" + city + '\'' +
                ", latitude = " + latitude +
                ", longitude = " + longitude +
                '}' + "\n";
    }
}



