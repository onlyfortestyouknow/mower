package com.games.mower.models;

public class MachineTask {
    private Machine mower;
    private String instructions;

    public MachineTask(Machine mower, String instructions) {
        this.mower = mower;
        this.instructions = instructions;
    }

    public Machine getMower() {
        return mower;
    }

    public String getInstructions() {
        return instructions;
    }
}
