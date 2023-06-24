package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try(Scanner input = new Scanner(new File("src/info.txt"))) {
            input.useDelimiter(",\\s|\n"); // comma + space delimiter

            Map<String, Airport> airports = new HashMap<>();
//            ArrayList<Airport> airports = new ArrayList<>();

            while(input.hasNext()) {
                String id = input.next();
//                System.out.println(id);

                String name = input.next();
//                System.out.println(name);

                String city = input.next();
//                System.out.println(city);

                double latitude = input.nextDouble();
//                System.out.println(latitude);

                double longitude = input.nextDouble();
//                System.out.println(longitude+"\n\n");

                airports.put(id, new Airport(id, name, city, latitude, longitude));
            }

            System.out.println(airports);
        } catch(Exception e) {
            e.printStackTrace();
        }



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

    }

