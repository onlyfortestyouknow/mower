package com.games.mower;

import com.games.mower.models.MachineTask;
import com.games.mower.services.MowerService;
import com.games.mower.handlers.InputHandler;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<MachineTask> machineTaskList = new InputHandler().getMowerTasks();
        MowerService mowerService = new MowerService(machineTaskList);
        mowerService.executeMowerTasks();

    }
}
