package com.games.mower.commands;

import com.games.mower.models.Mower;
import com.games.mower.services.MowerService;

public class TurnRightCommand implements Command {
    private Mower mower;

    public TurnRightCommand(Mower mower) {
        this.mower = mower;
    }

    @Override
    public void execute() {
        MowerService.turnRight(mower);
    }
}
