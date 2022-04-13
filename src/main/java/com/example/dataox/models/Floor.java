package com.example.dataox.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Floor {

    private List<Passenger> listOfPassengersOnFloor;

}
