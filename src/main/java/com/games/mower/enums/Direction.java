package com.games.mower.enums;

public enum Direction {
    N('N'), E('E'), S('S'), W('W');

    public char getDir() {
        return dir;
    }

    private final char dir;

    Direction(char dir) {
        this.dir = dir;
    }


}