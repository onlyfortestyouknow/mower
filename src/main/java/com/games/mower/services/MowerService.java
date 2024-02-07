package com.games.mower.services;

import com.games.mower.enums.Action;
import com.games.mower.enums.Direction;
import com.games.mower.models.Machine;
import com.games.mower.models.MachineTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.games.mower.enums.Direction.*;


public class MowerService {

    List<MachineTask> machineTaskList = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(MowerService.class);
    private static final String MOWER_RESULT_MESSAGE = "Mower {} final result {}";
    private static final String MACHINES_COLLISION_ERROR = "Collision between machines";

    public MowerService(List<MachineTask> machineTaskList) {
        this.machineTaskList = machineTaskList;
    }

    public MowerService() {
    }

    public void checkCollisions() {
        Map<String, Machine> positionMap = new HashMap<>();
        List<Machine> machines = machineTaskList.stream().map(task->task.getMachine()).collect(Collectors.toList());

        for (Machine machine : machines) {
            String positionKey = machine.getX() + "," + machine.getY();

            if (positionMap.containsKey(positionKey)) {
                throw new IllegalArgumentException(MACHINES_COLLISION_ERROR);
            } else {
                positionMap.put(positionKey, machine);
            }
        }

    }

    public void executeMowerTasks(){
        checkCollisions();
        for (int i = 0; i< machineTaskList.size(); i++) {
            executeMowerTask(machineTaskList.get(i));
            logger.info(MOWER_RESULT_MESSAGE, i+1, machineTaskList.get(i).getMachine());
        }
    }

    public void executeMowerTask(MachineTask machineTask) {
        Machine mower = machineTask.getMachine();
        String instructions = machineTask.getInstructions();
        for (char instructionChar : instructions.toCharArray()) {
            Action instruction = Action.valueOf(String.valueOf(instructionChar));
            switch (instruction) {
                case A:
                    advance(mower);
                    break;
                case D:
                    turnRight(mower);
                    break;
                case G:
                    turnLeft(mower);
                    break;
            }
        }
    }

    public void advance(Machine mower) {
        int x = mower.getX(), y = mower.getY();
        int maxX = mower.getMaxX(), maxY = mower.getMaxY();
        switch (mower.getDirection()) {
            case N:
                if (y < maxY) mower.setY(y + 1);
                break;
            case E:
                if (x < maxX) mower.setX(x + 1);
                break;
            case S:
                if (y > 0) mower.setY(y - 1);
                break;
            case W:
                if (x > 0) mower.setX(x - 1);
                break;
        }
        checkCollisions();
    }

    public void turnRight(Machine mower) {
        Direction newDirection = null;
        switch (mower.getDirection()) {
            case N:
                newDirection = E;
                break;
            case E:
                newDirection = S;
                break;
            case S:
                newDirection = W;
                break;
            case W:
                newDirection = Direction.N;
                break;
        }
        mower.setDirection(newDirection);
        checkCollisions();

    }

    public void turnLeft(Machine mower) {
        Direction newDirection = null;
        switch (mower.getDirection()) {
            case N:
                newDirection = W;
                break;
            case W:
                newDirection = S;
                break;
            case S:
                newDirection = E;
                break;
            case E:
                newDirection = N;
                break;
        }
        mower.setDirection(newDirection);
        checkCollisions();

    }
}
