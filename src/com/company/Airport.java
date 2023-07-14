package com.company;

import java.util.HashMap;
import java.util.Map;

public class Airport {
    String id;
    String name;
    String city;
    double latitude;
    double longitude;
    String timezone;
    Map<String, Double> flightTimes;

    public Airport(String id, String name, String city, String timezone, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.timezone = timezone;
        this.latitude = latitude;
        this.longitude = longitude;
        flightTimes = new HashMap<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Map<String, Double> getFlightTimes() {
        return flightTimes;
    }

    public void setFlightTimes(Map<String, Double> flightTimes) {
        this.flightTimes = flightTimes;
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



