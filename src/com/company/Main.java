package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {
    static final String WINDOWS_REGEX = ",\\s|\\r\\n";
    static final String MAC_REGEX = ",\\s|\\n";
    static final double EARTH_RADIUS_IN_MILES = 3958.8; // Radius of the Earth in miles
    static Random random = new Random();
    private static final Map<String, Airport> airports = new HashMap<>();
    public static void main(String[] args) {

        try(Scanner input = new Scanner(new File("src/info.txt")).useLocale(Locale.US);
            BufferedReader br = new BufferedReader(new FileReader("src/flightTimes.txt"))) {
            String theDelimiter = detectOS();
            input.useDelimiter(theDelimiter); // comma + space delimiter
            String str;
            String[] line;

            while(input.hasNext()) {
                String id = input.next();
//                System.out.println(id);

                String name = input.next();
//                System.out.println(name);

                String city = input.next();
//                System.out.println(city);

                String timezone = input.next();
//                System.out.println(timezone);

                double latitude = input.nextDouble();
//                System.out.println(latitude);

                double longitude = input.nextDouble();
//                System.out.println(longitude+"\n\n");

                Airport a = new Airport(id, name, city, timezone, latitude, longitude);
                if((str = br.readLine()) != null) {
                    line = str.split(",\\s");
                    for (String s : line) {
                        String[] item = s.split("=");
                        a.getFlightTimes().put(item[0], Double.parseDouble(item[1]));
                    }
                }
                airports.put(id, a);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        //TODO: move to separate method -> populate flight times
        for(Map.Entry<String, Airport> a : airports.entrySet()) {
            String abbreviationA = a.getKey();
            Airport airportA = a.getValue();
            for(Map.Entry<String, Airport> b : airports.entrySet()) {
                String abbreviationB = b.getKey();
                Airport airportB = b.getValue();

                if(abbreviationA.equals(abbreviationB) || airportA.getCity().equals(airportB.getCity())) continue;
                airportA.getFlightTimes().put(abbreviationB, calculateFlightTime(airportA, airportB));
            }
            System.out.println(abbreviationA + " => " +airportA.getFlightTimes());
        }


        // map to get flight by id
        Map<Integer, Flight> flightMap = generateFlights(10);



//        try(FileReader fileReader = new FileReader("src/info.txt")) {
//            }
//        catch(Exception e) {
//                e.printStackTrace();
//            }
//        }


//        try(FileWriter fileWriter = new FileWriter("src/data.sql")) {
//
//        } catch(Exception e) {
//            e.printStackTrace();
//          }
        }

        //TODO: NOT IMPLEMENTED
        public static void printAirports() {
            // NOT IMPLEMENTED
        }

        // used to initialize proper regex to handle difference in line ending syntax
        public static String detectOS() {
            return System.getProperty("os.name").charAt(0) == 'W' ? WINDOWS_REGEX : MAC_REGEX;
        }


        // calculate distance in miles using the Haversine Formula
        // lat, lat, long, long
        public static double calculateDistance(double x1, double x2, double y1, double y2) {
            double latDif = Math.toRadians(x2 - x1);
            double longDif = Math.toRadians(y2 - y1);

            x1 = Math.toRadians(x1);
            x2 = Math.toRadians(x2);

            double a = (Math.sin(latDif/2) * Math.sin(latDif/2)) + Math.cos(x1) * Math.cos(x2) * ((Math.sin(longDif/2) * Math.sin(longDif/2)));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            return EARTH_RADIUS_IN_MILES * c;
        }

        // calculate flight time based on average speed of 500 mph as well as taxi time
        public static double calculateFlightTime(Airport a, Airport b) {
            double airspeed = 500.0 / 60.0;
            double distance = calculateDistance(a.getLatitude(), b.getLatitude(), a.getLongitude(), b.getLongitude());
            return Math.ceil((distance / airspeed) + 40);
        }

        // TODO: Not implemented
        public static Map<Integer, Flight> generateFlights(int N) {
            Set<Integer> flightNumbers = new HashSet<>();
            Map<Integer, Flight> map = new HashMap<>();

            Airport[] airportArray = airports.values().toArray(new Airport[0]);
            return map;
        }
    }

