import com.games.mower.enums.Direction;
import com.games.mower.models.Mower;
import com.games.mower.services.MowerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTest {

    MowerService mowerService = new MowerService();

    @Nested
    @DisplayName("Test turn right")
    class testTurnRight {
        @Test
        public void testTurnRightFromNorth() {
            Mower mower = new Mower(0, 0, Direction.N, 5, 5);
            mowerService.turnRight(mower);
            assertEquals(Direction.E, mower.getDirection());
        }

        @Test
        public void testTurnRightFromEast() {
            Mower mower = new Mower(0, 0, Direction.E, 5, 5);
            mowerService.turnRight(mower);
            assertEquals(Direction.S, mower.getDirection());
        }

        @Test
        public void testTurnRightFromSouth() {
            Mower mower = new Mower(0, 0, Direction.S, 5, 5);
            mowerService.turnRight(mower);
            assertEquals(Direction.W, mower.getDirection());
        }

        @Test
        public void testTurnRightFromWest() {
            Mower mower = new Mower(0, 0, Direction.W, 5, 5);
            mowerService.turnRight(mower);
            assertEquals(Direction.N, mower.getDirection());
        }
    }

    @Nested
    @DisplayName("Test turn left")
    class testTurnLeft {
        @Test
        public void testTurnLeftFromNorth() {
            Mower mower = new Mower(0, 0, Direction.N, 5, 5);
            mowerService.turnLeft(mower);
            assertEquals(Direction.W, mower.getDirection());
        }

        @Test
        public void testTurnLeftFromWest() {
            Mower mower = new Mower(0, 0, Direction.W, 5, 5);
            mowerService.turnLeft(mower);
            assertEquals(Direction.S, mower.getDirection());
        }

        @Test
        public void testTurnLeftFromSouth() {
            Mower mower = new Mower(0, 0, Direction.S, 5, 5);
            mowerService.turnLeft(mower);
            assertEquals(Direction.E, mower.getDirection());
        }

        @Test
        public void testTurnLeftFromEast() {
            Mower mower = new Mower(0, 0, Direction.E, 5, 5);
            mowerService.turnLeft(mower);
            assertEquals(Direction.N, mower.getDirection());
        }
    }

    @Nested
    @DisplayName("Test Advance")
    class testAdvance {
        @Test
        public void testAdvanceNorthWithinBounds() {
            Mower mower = new Mower(2, 2, Direction.N, 5, 5);
            mowerService.advance(mower);
            assertEquals(2, mower.getX());
            assertEquals(3, mower.getY());
        }

        @Test
        public void testAdvanceEastWithinBounds() {
            Mower mower = new Mower(2, 2, Direction.E, 5, 5);
            mowerService.advance(mower);
            assertEquals(3, mower.getX());
            assertEquals(2, mower.getY());
        }

        @Test
        public void testAdvanceSouthWithinBounds() {
            Mower mower = new Mower(2, 2, Direction.S, 5, 5);
            mowerService.advance(mower);
            assertEquals(2, mower.getX());
            assertEquals(1, mower.getY());
        }

        @Test
        public void testAdvanceWestWithinBounds() {
            Mower mower = new Mower(2, 2, Direction.W, 5, 5);
            mowerService.advance(mower);
            assertEquals(1, mower.getX());
            assertEquals(2, mower.getY());
        }

        @Test
        public void testAdvanceNorthAtEdge() {
            Mower mower = new Mower(2, 5, Direction.N, 5, 5);
            mowerService.advance(mower);
            assertEquals(2, mower.getX());
            assertEquals(5, mower.getY());
        }

        @Test
        public void testAdvanceEastAtEdge() {
            Mower mower = new Mower(5, 2, Direction.E, 5, 5);
            mowerService.advance(mower);
            assertEquals(5, mower.getX());
            assertEquals(2, mower.getY());
        }

        @Test
        public void testAdvanceSouthAtEdge() {
            Mower mower = new Mower(2, 0, Direction.S, 5, 5);
            mowerService.advance(mower);
            assertEquals(2, mower.getX());
            assertEquals(0, mower.getY());
        }

        @Test
        public void testAdvanceWestAtEdge() {
            Mower mower = new Mower(0, 2, Direction.W, 5, 5);
            mowerService.advance(mower);
            assertEquals(0, mower.getX());
            assertEquals(2, mower.getY());
        }
    }


}
