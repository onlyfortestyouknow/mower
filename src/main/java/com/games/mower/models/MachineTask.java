package com.games.mower.models;

public class MachineTask {
    private Machine machine;
    private String instructions;

    public MachineTask(Machine machine, String instructions) {
        this.machine = machine;
        this.instructions = instructions;
    }

    public Machine getMachine() {
        return machine;
    }

    public String getInstructions() {
        return instructions;
    }
}
