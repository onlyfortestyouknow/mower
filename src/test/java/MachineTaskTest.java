import com.games.mower.enums.Direction;
import com.games.mower.models.Mower;
import com.games.mower.models.MachineTask;
import com.games.mower.services.MowerService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MachineTaskTest {

    MowerService mowerService = new MowerService();

    @Test
    public void testMowerMovesNorthCorrectly() {
        Mower mower = new Mower(1, 1, Direction.N, 5, 5);
        MachineTask task = new MachineTask(mower, "A");
        mowerService.executeMowerTask(task);
        assertEquals(1, mower.getX());
        assertEquals(2, mower.getY());
        assertEquals(Direction.N, mower.getDirection());
    }

    @Test
    public void testMowerTurnsRightCorrectly() {
        Mower mower = new Mower(1, 1, Direction.N, 5, 5);
        MachineTask task = new MachineTask(mower, "D");
        mowerService.executeMowerTask(task);
        assertEquals(1, mower.getX());
        assertEquals(1, mower.getY());
        assertEquals(Direction.E, mower.getDirection());
    }

    @Test
    public void testMowerTurnsLeftAndMovesSouthCorrectly() {
        Mower mower = new Mower(1, 1, Direction.N, 5, 5);
        MachineTask task = new MachineTask(mower, "GA");
        mowerService.executeMowerTask(task);
        assertEquals(0, mower.getX());
        assertEquals(1, mower.getY());
        assertEquals(Direction.W, mower.getDirection());
    }

    @Test
    public void testMowerSequenceOfCommandsOffBound() {
        Mower mower = new Mower(2, 2, Direction.E, 5, 5);
        MachineTask task = new MachineTask(mower, "AADAADADDA");
        mowerService.executeMowerTask(task);
        assertEquals(4, mower.getX());
        assertEquals(0, mower.getY());
        assertEquals(Direction.E, mower.getDirection());
    }

    @Test
    public void testMowerDoesNotMoveOutsideBoundary() {
        Mower mower = new Mower(0, 0, Direction.S, 5, 5);
        MachineTask task = new MachineTask(mower, "A");
        mowerService.executeMowerTask(task);
        assertEquals(0, mower.getX());
        assertEquals(0, mower.getY());
        assertEquals(Direction.S, mower.getDirection());
    }

    @Test
    public void testMultipleMowers() {
        Mower mower1 = new Mower(1, 2, Direction.N, 5, 5);
        Mower mower2 = new Mower(3, 3, Direction.E, 5, 5);
        MachineTask task1 = new MachineTask(mower1, "GAGAGAGAA");
        MachineTask task2 = new MachineTask(mower2, "AADAADADDA");

        mowerService = new MowerService(Arrays.asList(task1, task2));
        mowerService.executeMowerTasks();

        assertEquals("1 3 N", mower1.toString());
        assertEquals("5 1 E", mower2.toString());
    }

    @Test()
    public void testIntialCollision() {
        Mower mower1 = new Mower(1, 1, Direction.N, 5, 5);
        Mower mower2 = new Mower(1, 1, Direction.E, 5, 5);
        MachineTask task1 = new MachineTask(mower1, "GAGAGAGAA");
        MachineTask task2 = new MachineTask(mower2, "AADAADADDA");

        mowerService = new MowerService(Arrays.asList(task1, task2));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> mowerService.executeMowerTasks());
        assertEquals("Collision between machines", exception.getMessage());

    }

    @Test()
    public void testFurtherCollision() {
        Mower mower1 = new Mower(1, 3, Direction.S, 5, 5);
        Mower mower2 = new Mower(1, 1, Direction.N, 5, 5);
        MachineTask task1 = new MachineTask(mower1, "A");
        MachineTask task2 = new MachineTask(mower2, "A");

        mowerService = new MowerService(Arrays.asList(task1, task2));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> mowerService.executeMowerTasks());
        assertEquals("Collision between machines", exception.getMessage());

    }
}
