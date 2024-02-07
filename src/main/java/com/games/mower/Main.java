package com.games.mower;

import com.games.mower.models.MowerTask;
import com.games.mower.services.MowerService;
import com.games.mower.handlers.InputHandler;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<MowerTask> mowerTaskList = new InputHandler().getMowerTasks();
        MowerService.executeMowerTasks(mowerTaskList);

    }
}
