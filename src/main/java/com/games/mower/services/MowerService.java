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

public class MowerService implements IMachineService {

    private List<MachineTask> mowerTaskList;

    private static final Logger logger = LogManager.getLogger(MowerService.class);
    private static final String MOWER_RESULT_MESSAGE = "Mower {} final result {}";
    private static final String MACHINES_COLLISION_ERROR = "Collision between machines";
    private static final String SKIP_NULL_TASK_ERROR = "Skipping null task at index {}";
    private static final String INVALID_MACHINE_TASK_ERROR = "Machine task or its essential properties are null.";
    private static final String INVALID_MOWER_ERROR = "Mower is null";

    public MowerService(List<MachineTask> mowerTaskList) {
        checkIfMowerTaskIsValid(mowerTaskList);
        this.mowerTaskList = new ArrayList<>(mowerTaskList);
    }

    public MowerService() {
        mowerTaskList = new ArrayList<>();
    }

    /**
     * Check for collisions between machines.
     * Collision: more than one machine at the same position
     */
    public void checkCollisions() {
        Map<String, Machine> positionMap = new HashMap<>();
        for (MachineTask task : mowerTaskList) {
            if (task == null || task.getMachine() == null) {
                logger.error(INVALID_MACHINE_TASK_ERROR);
                continue;
            }
            Machine machine = task.getMachine();
            String positionKey = machine.getX() + "," + machine.getY();

            if (positionMap.containsKey(positionKey)) {
                throw new IllegalArgumentException(MACHINES_COLLISION_ERROR);
            } else {
                positionMap.put(positionKey, machine);
            }
        }
    }

    /**
     * Execute tasks for each machine and log the results.
     */
    public void executeMowerTasks() {
        checkCollisions();
        for (int i = 0; i < mowerTaskList.size(); i++) {
            MachineTask task = mowerTaskList.get(i);
            if (task == null) {
                logger.error(SKIP_NULL_TASK_ERROR, i);
                continue;
            }
            executeMowerTask(task);
            logger.info(MOWER_RESULT_MESSAGE, i + 1, task.getMachine());
        }
    }

    /**
     * Execute a specific machine task, handling movement instructions.
     *
     * @param machineTask The machine task to be executed
     */
    public void executeMowerTask(MachineTask machineTask) {
        if (machineTask.getMachine() == null || machineTask.getInstructions() == null) {
            logger.error(INVALID_MOWER_ERROR);
            return;
        }
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

    /**
     * Move the mower forward, checking for collisions.
     *
     * @param mower The machine (mower) to be moved
     */
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

    /**
     * Turn the mower to the right.
     *
     * @param mower The machine (mower) to be turned
     */
    public void turnRight(Machine mower) {
        Direction newDirection = null;
        switch (mower.getDirection()) {
            case N:
                newDirection = Direction.E;
                break;
            case E:
                newDirection = Direction.S;
                break;
            case S:
                newDirection = Direction.W;
                break;
            case W:
                newDirection = Direction.N;
                break;
        }
        mower.setDirection(newDirection);
    }


    /**
     * Turn the mower to the left.
     *
     * @param mower The machine (mower) to be turned
     */
    public void turnLeft(Machine mower) {
        Direction newDirection = null;
        switch (mower.getDirection()) {
            case N:
                newDirection = Direction.W;
                break;
            case W:
                newDirection = Direction.S;
                break;
            case S:
                newDirection = Direction.E;
                break;
            case E:
                newDirection = Direction.N;
                break;
        }
        mower.setDirection(newDirection);
    }

    public void setMowerTaskList(List<MachineTask> mowerTaskList) {
        checkIfMowerTaskIsValid(mowerTaskList);
        this.mowerTaskList = new ArrayList<>(mowerTaskList);
    }

    /**
     * Check if the provided list of machine tasks is valid.
     *
     * @param mowerTaskList List of machine tasks to be checked
     */
    private void checkIfMowerTaskIsValid(List<MachineTask> mowerTaskList) {
        if (mowerTaskList == null) throw new IllegalArgumentException(INVALID_MACHINE_TASK_ERROR);
        for (MachineTask mowerTask : mowerTaskList)
            if (mowerTask == null || mowerTask.getMachine() == null || mowerTask.getInstructions() == null)
                throw new IllegalArgumentException(INVALID_MACHINE_TASK_ERROR);
    }
}
