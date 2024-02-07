package com.games.mower.commands;

import com.games.mower.services.MowerService;
import com.games.mower.models.Mower;

public class AdvanceCommand implements Command {
    private Mower mower;

    public AdvanceCommand(Mower mower) {
        this.mower = mower;
    }

    @Override
    public void execute() {
        MowerService.advance(mower);
    }
}
