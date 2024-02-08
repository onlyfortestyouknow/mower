package com.games.mower.services;

import com.games.mower.models.Machine;
import com.games.mower.models.MachineTask;

import java.util.List;

public interface IMachineService {
    void checkCollisions();
    void executeMowerTasks();
    void executeMowerTask(MachineTask machineTask);
    void advance(Machine mower);
    void turnRight(Machine mower);
    void turnLeft(Machine mower);

    void setMowerTaskList(List<MachineTask> asList);
}
