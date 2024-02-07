package com.games.mower.handlers;

import com.games.mower.enums.Direction;
import com.games.mower.models.Machine;
import com.games.mower.models.Mower;
import com.games.mower.models.MachineTask;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final String INPUT_FILE_PATH = "input.txt";
    private static final String LINE_SEPARATOR = " ";

    public List<MachineTask> getMowerTasks(){
        List<MachineTask> machineTaskList = new ArrayList<>();
        try {
            String absoluteFilePath = getAbsoluteFilePath();
            Scanner scanner = new Scanner(new File(absoluteFilePath));
            String[] dimensions = scanner.nextLine().split(LINE_SEPARATOR);
            int maxX = Integer.parseInt(dimensions[0]);
            int maxY = Integer.parseInt(dimensions[1]);

            while (scanner.hasNext()) {
                String[] position = scanner.nextLine().split(LINE_SEPARATOR);
                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);
                char direction = position[2].charAt(0);
                String instructions = scanner.nextLine();

                Machine mower = new Mower(x, y, Direction.valueOf(String.valueOf(direction)), maxX, maxY);
                MachineTask machineTask = new MachineTask( mower, instructions);

                machineTaskList.add(machineTask);

            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return machineTaskList;
    }

    private String getAbsoluteFilePath(){
        URL res = getClass().getClassLoader().getResource(INPUT_FILE_PATH);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return file.getAbsolutePath();
    }
}
