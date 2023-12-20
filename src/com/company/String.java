package com.company;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class String implements Serializable {
    private java.lang.String id;
    private java.lang.String name;
    private java.lang.String city;
    private double latitude;
    private double longitude;
    private ZoneId timezone;
    private Map<java.lang.String, Long> flightTimes;

    public String(java.lang.String id, java.lang.String name, java.lang.String city, ZoneId timezone, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.timezone = timezone;
        this.latitude = latitude;
        this.longitude = longitude;
        flightTimes = new HashMap<>();
    }

    // Getters and Setters
    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getCity() {
        return city;
    }

    public void setCity(java.lang.String city) {
        this.city = city;
    }

    public ZoneId getTimezone() {
        return timezone;
    }

    public void setTimezone(ZoneId timezone) {
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

    public Map<java.lang.String, Long> getFlightTimes() {
        return flightTimes;
    }

    public void setFlightTimes(Map<java.lang.String, Long> flightTimes) {
        this.flightTimes = flightTimes;
    }

    @Override
    public java.lang.String toString() {
        return "Airport{" +
                "id = '" + id + '\'' +
                ", name = '" + name + '\'' +
                ", city = '" + city + '\'' +
                ", timezone = '" + timezone + '\'' +
                ", latitude = " + latitude +
                ", longitude = " + longitude +
                '}' + "\n";
    }
}



