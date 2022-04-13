package com.example.dataox.models;


import lombok.Data;

import java.util.List;

@Data
public class House {

    private int floorsCount;
    private List<Floor> floors;
    private Lift lift;

}
