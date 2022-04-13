package com.example.dataox.service;

import com.example.dataox.models.House;
import com.example.dataox.models.Lift;

public class PrintService {
    public static void printStep(House house) {

        StringBuilder description = new StringBuilder();
        Lift lift = house.getLift();

        for (int i = house.getFloorsCount() - 1; i >= 0; i--) {
            description.append(i);
            description.append("| ");

            boolean isCurrent = i == lift.getCurrentFloor();
            if (isCurrent) {
                description.append(lift.isItUpButton() ? "^ " : "v ");
                description.append(lift.getPassengers().size());
                description.append(" | ");
            } else {
                description.append("    | ");
            }

            description.append(house.getFloors().get(i).getListOfPassengersOnFloor().size());

            if (isCurrent) {
                description.append(", current floor, passengers in lift want to: ");
                lift.getPassengers().forEach(x -> description.append(x.getGoalFloor()).append(" "));
            }

            description.append('\n');
        }

        System.out.println(description);
    }
}