package com.example.connectfour547;

import java.util.Random;

public class AI {

    Board test;
    public AI(Board board) {
        test = board;
    }

    public int getmove()
    {
        Random random = new Random();
        return random.nextInt(test.getcols());
    }
}
