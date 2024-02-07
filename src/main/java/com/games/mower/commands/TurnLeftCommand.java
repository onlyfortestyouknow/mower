package com.games.mower.commands;

import com.games.mower.models.Mower;
import com.games.mower.services.MowerService;

public class TurnLeftCommand implements Command {
    private Mower mower;

    public TurnLeftCommand(Mower mower) {
        this.mower = mower;
    }

    @Override
    public void execute() {
        MowerService.turnLeft(mower);
    }
}