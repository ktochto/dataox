package com.example.dataox.service;

import com.example.dataox.models.Floor;
import com.example.dataox.models.House;
import com.example.dataox.models.Lift;
import com.example.dataox.models.Passenger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.dataox.service.PrintService.printStep;

public class LiftService {

    public static void lift() {

        House house = generateOriginLocations();

        System.out.println();
        System.out.println("Floors: " + house.getFloorsCount());

        System.out.println();
        System.out.println("*** Step " + 0 + " ***");
        printStep(house);

        var step = 1;
        while (house.getLift().isMoving()) {
            System.out.println();
            System.out.println("*** Step " + step + " ***");

            step(house);
            printStep(house);
            step++;
        }
    }

    private static House generateOriginLocations() {
        ArrayList<Floor> floors = new ArrayList<>();
        House house = new House();

        house.setFloorsCount(new Random().ints(5, 20).findFirst().getAsInt());

        for (int i = 0; i < house.getFloorsCount(); i++) {
            ArrayList<Passenger> listOfPassengersOnFloor = new ArrayList<>();

            for (int y = 0; y < new Random().ints(0, 10).findFirst().getAsInt(); y++) {
                int random = new Random().ints(0, house.getFloorsCount() - 1).findFirst().getAsInt();
                while (random == i)
                    random = new Random().ints(0, house.getFloorsCount() - 1).findFirst().getAsInt();

                Passenger passenger = new Passenger(random);
                listOfPassengersOnFloor.add(y, passenger);
            }

            floors.add(i, new Floor(listOfPassengersOnFloor));
        }

        house.setFloors(floors);
        house.setLift(new Lift(new LinkedList<>(), true, true, 0));
        return house;
    }

    private static void step(House house) {
        arriveOnFloor(house);

        var lift = house.getLift();
        if (lift.getPassengers().isEmpty()) {
            house.getFloors().parallelStream().filter(x -> !x.getListOfPassengersOnFloor().isEmpty()).findAny().ifPresentOrElse(
                    x -> {
                        int destFloor = house.getFloors().indexOf(x);
                        lift.setItUpButton(destFloor > lift.getCurrentFloor());
                        lift.setCurrentFloor(destFloor);
                    },
                    () -> lift.setMoving(false));
            return;
        }

        Comparator<Passenger> comparator = Comparator.comparingInt(Passenger::getGoalFloor);
        Stream<Passenger> passengerStream = lift.getPassengers().stream();
        if (lift.isItUpButton() && (passengerStream.max(comparator).get().getGoalFloor() > lift.getCurrentFloor())) {
            lift.setCurrentFloor(lift.getCurrentFloor() + 1);
        } else if (!lift.isItUpButton() && (passengerStream.min(comparator).get().getGoalFloor() < lift.getCurrentFloor())) {
            lift.setCurrentFloor(lift.getCurrentFloor() - 1);
        }
    }

    private static void arriveOnFloor(House house) {
        Lift lift = house.getLift();
        lift.setPassengers(lift.getPassengers().stream()
                .filter(x -> x.getGoalFloor() != lift.getCurrentFloor())
                .collect(Collectors.toList()));

        List<Passenger> remove = new ArrayList<>();
        List<Passenger> listOfPassengersOnFloor = house.getFloors().get(lift.getCurrentFloor()).getListOfPassengersOnFloor();

        for (Passenger passenger : listOfPassengersOnFloor) {
            if (lift.getCapacity() == lift.getPassengers().size()) {
                break;
            }
            if ((lift.isItUpButton() && passenger.getGoalFloor() > lift.getCurrentFloor()) ||
                    (!lift.isItUpButton() && passenger.getGoalFloor() < lift.getCurrentFloor())) {
                movePassenger(lift, remove, passenger);
            } else if (lift.getPassengers().isEmpty()) {
                movePassenger(lift, remove, passenger);
                lift.setItUpButton(passenger.getGoalFloor() > lift.getCurrentFloor());
            }
        }
        listOfPassengersOnFloor.removeAll(remove);
    }

    private static void movePassenger(Lift lift, List<Passenger> remove, Passenger passenger) {
        lift.getPassengers().add(passenger);
        remove.add(passenger);
    }
}
