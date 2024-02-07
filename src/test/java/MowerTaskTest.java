import com.games.mower.enums.Direction;
import com.games.mower.models.Mower;
import com.games.mower.models.MowerTask;
import com.games.mower.services.MowerService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTaskTest {
    @Test
    public void testMowerMovesNorthCorrectly() {
        Mower mower = new Mower(1, 1, Direction.N, 5, 5);
        MowerTask task = new MowerTask(mower, "A");
        MowerService.executeMowerTask(task);
        assertEquals(1, mower.getX());
        assertEquals(2, mower.getY());
        assertEquals(Direction.N, mower.getDirection());
    }

    @Test
    public void testMowerTurnsRightCorrectly() {
        Mower mower = new Mower(1, 1, Direction.N, 5, 5);
        MowerTask task = new MowerTask(mower, "D");
        MowerService.executeMowerTask(task);
        assertEquals(1, mower.getX());
        assertEquals(1, mower.getY());
        assertEquals(Direction.E, mower.getDirection());
    }

    @Test
    public void testMowerTurnsLeftAndMovesSouthCorrectly() {
        Mower mower = new Mower(1, 1, Direction.N, 5, 5);
        MowerTask task = new MowerTask(mower, "GA");
        MowerService.executeMowerTask(task);
        assertEquals(0, mower.getX());
        assertEquals(1, mower.getY());
        assertEquals(Direction.W, mower.getDirection());
    }

    @Test
    public void testMowerSequenceOfCommandsOffBound() {
        Mower mower = new Mower(2, 2, Direction.E, 5, 5);
        MowerTask task = new MowerTask(mower, "AADAADADDA");
        MowerService.executeMowerTask(task);
        assertEquals(4, mower.getX());
        assertEquals(0, mower.getY());
        assertEquals(Direction.E, mower.getDirection());
    }

    @Test
    public void testMowerDoesNotMoveOutsideBoundary() {
        Mower mower = new Mower(0, 0, Direction.S, 5, 5);
        MowerTask task = new MowerTask(mower, "A");
        MowerService.executeMowerTask(task);
        assertEquals(0, mower.getX());
        assertEquals(0, mower.getY());
        assertEquals(Direction.S, mower.getDirection());
    }

    @Test
    public void testMultipleMowers() {
        Mower mower1 = new Mower(1, 2, Direction.N, 5, 5);
        Mower mower2 = new Mower(3, 3, Direction.E, 5, 5);
        MowerTask task1 = new MowerTask(mower1, "GAGAGAGAA");
        MowerTask task2 = new MowerTask(mower2, "AADAADADDA");

        MowerService.executeMowerTasks(Arrays.asList(task1, task2));

        assertEquals("1 3 N", mower1.toString());
        assertEquals("5 1 E", mower2.toString());
    }
}
