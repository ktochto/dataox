package com.example.dataox.models;

import lombok.Data;

@Data
public class Lift {

    private final int capacity = 5;
    private int howManyPassNow;
    private Button button;

}
