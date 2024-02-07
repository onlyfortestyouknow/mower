package com.games.mower.models;

import com.games.mower.enums.Direction;

public class Mower extends Machine{

    public Mower(int x, int y, Direction direction, int maxX, int maxY) {
        super(x,y,direction,maxX,maxY);
    }


    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
