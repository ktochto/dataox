package com.example.dataox.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class Floor {

    private HashMap<Button, Passenger> countOfPassengers;

}
