package com.games.mower.models;

public class MowerTask {
    private Mower mower;
    private String instructions;

    public MowerTask(Mower mower, String instructions) {
        this.mower = mower;
        this.instructions = instructions;
    }

    public Mower getMower() {
        return mower;
    }

    public String getInstructions() {
        return instructions;
    }
}
