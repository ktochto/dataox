package com.example.dataox.models;


import lombok.Data;

import java.util.HashMap;

@Data
public class House {

    private int floorsCount;
    private HashMap<Integer, Floor> floors;

}
