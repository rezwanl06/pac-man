package ghost.objects;

import org.junit.jupiter.api.*;

import ghost.objects.Ghost.Mode;
import ghost.objects.Moving.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class GhostTests {
    @Test
    void checkGhostsMode() {
        // Check that all ghosts start in Scatter mode
        Ghost a = new Ambusher(300, 200, null, 1);
        Ghost c = new Chaser(400, 200, null, 1);
        Ghost i = new Ignorant(200, 200, null, 1);
        Ghost w = new Whim(300, 200, null, 1);
        assertEquals(a.mode, Mode.Scatter);
        assertEquals(c.mode, Mode.Scatter);
        assertEquals(i.mode, Mode.Scatter);
        assertEquals(w.mode, Mode.Scatter);
    }

    @Test
    void move() {
        // Check that the ghosts move accordingly
        Ghost whim = new Whim(200, 300, null, 1);
        assertEquals(Direction.Down, whim.getDirection());
        GameMap map = new GameMap();
        Waka player = new Waka(400, 300, null, 1, 3);
        whim.move(player);
        assertArrayEquals(new int[] {200, 301}, whim.getCurrentCoordinates());
        whim.changeDirection(Direction.Up);
        whim.move(player);
        assertArrayEquals(new int[] {200, 300}, whim.getCurrentCoordinates());
        whim.changeDirection(Direction.Left);
        whim.move(player);
        assertArrayEquals(new int[] {199, 300}, whim.getCurrentCoordinates());
        whim.changeDirection(Direction.Right);
        whim.move(player);
        assertArrayEquals(new int[] {200, 300}, whim.getCurrentCoordinates());
    }

    @Test
    void logicCheck() {
        // Check that the tick method works correctly, and the ghost changes mode accordingly
        GameMap map = new GameMap();
        Ghost chaser = new Chaser(200, 300, null, 1);
        for (int i = 0; i < 500; i++) {
            map.tick();
            chaser.tick();
        }
        assertEquals(Mode.Chase, chaser.mode);
        assertEquals(0, chaser.scatterTimer);
        for (int i = 0; i < 1000; i++) {
            map.tick();
            chaser.tick();
        }
        assertEquals(Mode.Scatter, chaser.mode);
        assertEquals(0, chaser.chaseTimer);
    }

    @Test
    void frightenedCheck() {
        GameMap map = new GameMap();
        Ghost chaser = new Chaser(200, 300, null, 1);
        Waka waka = new Waka(18, 275, null, 1, 3);
        GameMap.ghosts.add(chaser);
        waka.tick();
        assertEquals(Mode.Frightened, chaser.mode);
    }
}
