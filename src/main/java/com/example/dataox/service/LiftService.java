package com.example.dataox.service;

import com.example.dataox.models.Button;
import com.example.dataox.models.Floor;
import com.example.dataox.models.House;
import com.example.dataox.models.Passenger;

import java.util.HashMap;
import java.util.Random;

public class LiftService {

    public static void lift() {

        House house = generateOriginLocations();

        System.out.println();
        System.out.println("Floors: " + house.getFloorsCount());

        System.out.println();
        System.out.println("*** Step " + 0 + " ***");
        printStep(house);

        System.out.println();
        System.out.println("*** Step " + 1 + " ***");
        printStep(house);

    }

    private static House generateOriginLocations() {
        Random random = new Random();
        House house = new House();
        HashMap<Integer, Floor> floors = new HashMap<>();

        house.setFloorsCount(random.ints(3, 5).findFirst().getAsInt());

        for (int i = 0; i < house.getFloorsCount(); i++) {
            HashMap<Button, Passenger> countOfPassengers = new HashMap<>();

            for (int y = 0; y < random.ints(0, 10).findFirst().getAsInt(); y++) {
                Button button = new Button(random.nextBoolean(), random.nextBoolean());
                Passenger passenger = new Passenger(random.ints(3, 5).findFirst().getAsInt());
                countOfPassengers.put(button, passenger);
            }

            floors.put(i, new Floor(countOfPassengers));
        }

        house.setFloors(floors);
        return house;
    }

    private static void printStep(House house) {

        for (int i = 0; i < house.getFloorsCount(); i++) {
            HashMap<Integer, Floor> floors = house.getFloors();

            if (floors.get(i).getCountOfPassengers().size() > 5)
                System.out.println("| | " + 5 + " " + (floors.get(i).getCountOfPassengers().size() - 5) + "");
            else if (floors.get(i).getCountOfPassengers().size() == 0)
                System.out.println("| | ");
            else
                System.out.println("| | " + floors.get(i).getCountOfPassengers().size() + "");
        }

    }

}
