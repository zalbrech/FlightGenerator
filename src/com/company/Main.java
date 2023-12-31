package com.company;

import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    static final java.lang.String WINDOWS_REGEX = ",\\s|\\r\\n";
    static final java.lang.String MAC_REGEX = ",\\s|\\n";
    static final double EARTH_RADIUS_IN_MILES = 3958.8; // Radius of the Earth in miles
    static Random random = new Random();
    private static final Map<java.lang.String, String> airportMap = new HashMap<>();
    private static final List<java.lang.String> airportList = new LinkedList<>();

    public static void main(java.lang.String[] args) {

        try (Scanner input = new Scanner(new File("src/info.txt")).useLocale(Locale.US);
             BufferedReader br = new BufferedReader(new FileReader("src/flightTimes.txt"))) {
            java.lang.String theDelimiter = detectOS();
            input.useDelimiter(theDelimiter); // comma + space delimiter
            java.lang.String str;
            java.lang.String[] line;

            while (input.hasNext()) {
                String a = createAirportFromFile(input, br);
                java.lang.String id = a.getId();

                airportList.add(id);
                airportMap.put(id, a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        printAirports();
//
//        for(Airport a : airports.values()) {
//            System.out.println(a.getId() + " => " + a.getFlightTimes());
//        }

        //TODO: move to separate method -> populate flight times

//        for(int i = 0; i < airportList.size(); i++) {
//            Airport airportA = airportMap.get(airportList.get(i));
//            String abbreviationA = airportA.getId();
//            airportA.setFlightTimes(new HashMap<>());
//            for(int j = 0; j < airportList.size(); j++) {
//                if(i == j) continue;
//                Airport airportB = airportMap.get(airportList.get(j));
//                String abbreviationB = airportB.getId();
//                if(abbreviationA.equals(abbreviationB) || airportA.getCity().equals(airportB.getCity())) continue;
//                airportA.getFlightTimes().put(abbreviationB, calculateFlightTime(airportA, airportB));
//            }
//            System.out.println(abbreviationA + " => " +airportA.getFlightTimes());
//        }
//
//          // DEPRECATED
//        for(Map.Entry<String, Airport> a : airportMap.entrySet()) {
//            String abbreviationA = a.getKey();
//            Airport airportA = a.getValue();
//            airportA.setFlightTimes(new HashMap<>());
//            System.out.println(airportA);
//            for(Map.Entry<String, Airport> b : airportMap.entrySet()) {
//                String abbreviationB = b.getKey();
//                Airport airportB = b.getValue();
//
//                System.out.println(abbreviationA + " " + abbreviationB);
//                if(abbreviationA.equals(abbreviationB) || airportA.getCity().equals(airportB.getCity())) {
////                    System.out.println("skipping");
//                    continue;
//                }
////                if(abbreviationA.equals(abbreviationB)) {
////                    System.out.println(abbreviationA + " = " + abbreviationB);
////                }
//                if(!abbreviationA.equals(abbreviationB) && !(airportA.getCity().equals(airportB.getCity()))) {
////                    System.out.println("adding flight time for " + abbreviationA + " & " + abbreviationB);
//                    airportA.getFlightTimes().put(abbreviationB, calculateFlightTime(airportA, airportB));
//                }
//            }
//            System.out.println(abbreviationA + " => " +airportA.getFlightTimes());
//        }

        // map to get flight by id
        Map<Integer, Flight> flightMap = generateFlights(10);

        createDataFile(flightMap);
    }

    // Creates Airport object with data from file
    // TODO: Overload to allow for manual creation. Will need to move populateFlightTimes to another method
    public static String createAirportFromFile(Scanner input, BufferedReader br) throws IOException {
        java.lang.String str = "";
        java.lang.String[] line;
        java.lang.String id = input.next();
//                System.out.println(id);

        java.lang.String name = input.next();
//                System.out.println(name);

        java.lang.String city = input.next();
//                System.out.println(city);

        ZoneId timezone = ZoneId.of(input.next());
//                System.out.println(timezone);

        double latitude = input.nextDouble();
//                System.out.println(latitude);

        double longitude = input.nextDouble();
//                System.out.println(longitude+"\n\n");
        String a = new String(id, name, city, timezone, latitude, longitude);
//            System.out.println(a);
        if ((str = br.readLine()) != null) {
            line = str.split(",\\s");
            for (java.lang.String s : line) {
                java.lang.String[] item = s.split("=");
                a.getFlightTimes().put(item[0], Long.parseLong(item[1]));
//                    System.out.println(a.getId() + " -> " + item[0] + " " + Long.parseLong(item[1]));
            }
        }
//            System.out.println("\n");
        return a;
    }

    public static void printAirports() {
        for (String a : airportMap.values()) {
            System.out.println(a);
        }
    }

    // used to initialize proper regex to handle difference in line ending syntax
    public static java.lang.String detectOS() {
        return System.getProperty("os.name").charAt(0) == 'W' ? WINDOWS_REGEX : MAC_REGEX;
    }


    // calculate distance in miles using the Haversine Formula
    public static double calculateDistance(double lat1, double lat2, double long1, double long2) {
        double latDif = Math.toRadians(lat2 - lat1);
        double longDif = Math.toRadians(long2 - long1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = (Math.sin(latDif / 2) * Math.sin(latDif / 2)) + Math.cos(lat1) * Math.cos(lat2) * ((Math.sin(longDif / 2) * Math.sin(longDif / 2)));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_IN_MILES * c;
    }

    // calculate flight time based on average speed of 500 mph as well as taxi time
    public static long calculateFlightTime(String a, String b) {
        double airspeed = 500.0 / 60.0;
        double distance = calculateDistance(a.getLatitude(), b.getLatitude(), a.getLongitude(), b.getLongitude());
        return Math.round((distance / airspeed) + 40);
    }

    // TODO: Not implemented
    public static Map<Integer, Flight> generateFlights(int N) {
        TreeSet<Integer> flightNumbers = new TreeSet<>();
        TreeSet<Double> prices = new TreeSet<>();
        Map<Integer, Flight> map = new HashMap<>();
        int a, b;
        int len = airportMap.size();
        String[] airportArray = airportMap.values().toArray(new String[0]);

        for (int i = 0; i < N; i++) {
            System.out.println(i);
            a = random.nextInt(len);
            b = random.nextInt(len);
            while (a == b) {
                b = random.nextInt(len);
            }

            String originAirport = airportArray[a];
//            System.out.println(originAirport.getId());

            String destinationAirport = airportArray[b];
//            System.out.println(destinationAirport.getId());

            if (originAirport.getFlightTimes().get(destinationAirport.getId()) == null ||
                    destinationAirport.getFlightTimes().get(originAirport.getId()) == null) {
                continue;
            }

            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusYears(2);

            LocalTime startTime = LocalTime.of(5, 0);
            LocalTime endTime = LocalTime.of(22, 0);

            ZonedDateTime theDepartureTime = ZonedDateTime.of(createRandomDate(startDate, endDate),
                    createRandomTime(startTime, endTime), originAirport.getTimezone());
            long theDuration = originAirport.getFlightTimes().get(destinationAirport.getId());
//            System.out.println(theDuration);

            ZonedDateTime theArrivalTime = theDepartureTime.withZoneSameInstant(destinationAirport.getTimezone()).plusMinutes(theDuration);

//            System.out.println(originAirport.getName() + "(" + originAirport.getId() + ") => " + theDepartureTime);
//            System.out.println("Duration in minutes => " + theDuration);
//            System.out.println(destinationAirport.getName() + "(" + destinationAirport.getId() + ") => " + theArrivalTime);

            double thePrice = generatePrice(theDuration, theDepartureTime.getDayOfYear());
//            System.out.println("price = " + thePrice);
            prices.add(thePrice);

            int flightNumber = generateFlightNumber(90000);
            while (!flightNumbers.add(flightNumber)) {
                flightNumber = generateFlightNumber(90000);
            }

            Flight flight = new Flight(originAirport,destinationAirport,theDepartureTime,theArrivalTime,theDuration,thePrice,flightNumber);

            map.put(flightNumber,flight);
        }
//        debugging only
//        System.out.println(flightNumbers);
//        System.out.println(prices);

        return map;
    }

    public static void createDataFile(Map<Integer, Flight> map) {
        try(FileWriter writer = new FileWriter("src/data.sql")) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // create random date between two defined bounds
    public static LocalDate createRandomDate(LocalDate startInclusive, LocalDate endInclusive) {
        long startDate = startInclusive.toEpochDay();
        long endDate = endInclusive.toEpochDay();

        long randomDate = startDate + (long) (random.nextDouble() * (endDate - startDate));

        return LocalDate.ofEpochDay(randomDate);
    }

    // create random time between two defined bounds
    public static LocalTime createRandomTime(LocalTime startInclusive, LocalTime endInclusive) {
        long startTime = startInclusive.toSecondOfDay();
        long endTime = endInclusive.toSecondOfDay();

        long randomTime = startTime + (long) (random.nextDouble() * (endTime - startTime));

        return LocalTime.ofSecondOfDay(randomTime).truncatedTo(ChronoUnit.MINUTES);
    }

    public static int generateFlightNumber(int upperBound) {
        return random.nextInt(upperBound) + 10000;
    }

    public static double generatePrice(long theDuration, int departureDay) {
        int timePremium = (90 - (departureDay - LocalDate.now().getDayOfYear()));
        return 100 + (Math.round((theDuration * .6) + (Math.max(timePremium, 0)) + random.nextInt(40)));
    }
}

