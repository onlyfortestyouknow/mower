package com.games.mower.services;

import com.games.mower.commands.TurnRightCommand;
import com.games.mower.commands.AdvanceCommand;
import com.games.mower.commands.Command;
import com.games.mower.commands.TurnLeftCommand;
import com.games.mower.enums.Action;
import com.games.mower.enums.Direction;
import com.games.mower.models.Mower;
import com.games.mower.models.MowerTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.games.mower.enums.Direction.*;


public class MowerService {

    private static final Logger logger = LogManager.getLogger(MowerService.class);

    public static void executeMowerTasks(List<MowerTask> mowerTaskList){
        for (int i=0; i<mowerTaskList.size(); i++) {
            executeMowerTask(mowerTaskList.get(i));
            logger.info("Mower {} final result {}", i+1, mowerTaskList.get(i).getMower());
        }
    }

    public static void executeMowerTask(MowerTask mowerTask) {
        Mower mower = mowerTask.getMower();
        String instructions = mowerTask.getInstructions();
        for (char instructionChar : instructions.toCharArray()) {
            Action instruction = Action.valueOf(String.valueOf(instructionChar));
            Command command = null;
            switch (instruction) {
                case A:
                    command = new AdvanceCommand(mower);
                    break;
                case D:
                    command = new TurnRightCommand(mower);
                    break;
                case G:
                    command = new TurnLeftCommand(mower);
                    break;
            }
            command.execute();
        }
    }

    public static void advance(Mower mower) {
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
    }

    public static void turnRight(Mower mower) {
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
    }

    public static void turnLeft(Mower mower) {
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
    }
}
