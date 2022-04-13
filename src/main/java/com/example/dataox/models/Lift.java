package com.example.dataox.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Lift {

    private final int capacity = 5;
    private List<Passenger> passengers;
    private boolean isItUpButton;
    private boolean isMoving;
    private int currentFloor;

}
