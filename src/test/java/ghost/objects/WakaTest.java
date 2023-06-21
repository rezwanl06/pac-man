package ghost.objects;

import org.junit.jupiter.api.*;

import ghost.objects.Moving.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class WakaTest {
    @Test
    void getters() {
        // Check the getters of Waka
        Waka waka = new Waka(300, 200, null, 1, 3);
        assertEquals(3, waka.getLives());
        assertEquals(Direction.Left, waka.getDirection());
        assertArrayEquals(new int[] {300, 200}, waka.getCurrentCoordinates());
    }

    @Test
    void checkNormalMovement() {
        // Check that the player moves accordingly under normal circumstances
        Waka waka = new Waka(300, 200, null, 1, 3);
        waka.tick();
        assertArrayEquals(new int[] {299, 200}, waka.getCurrentCoordinates());
        waka.changeDirection(Direction.Down);
        waka.tick();
        assertArrayEquals(new int[] {299, 201}, waka.getCurrentCoordinates());
        waka.changeDirection(Direction.Right);
        waka.tick();
        assertArrayEquals(new int[] {300, 201}, waka.getCurrentCoordinates());
        waka.changeDirection(Direction.Up);
        waka.tick();
        assertArrayEquals(new int[] {300, 200}, waka.getCurrentCoordinates());
    }

    @Test
    void checkLeftEdgeMovement() {
        // Ensure Waka can move to the right from the left edge
        Waka waka = new Waka(10, 100, null, 1, 3);
        waka.changeDirection(Direction.Right);
        waka.tick();
        assertArrayEquals(new int[] {11, 100}, waka.getCurrentCoordinates());
    }

    @Test
    void checkRightEdgeMovement() {
        // Ensure Waka can move to the right from the left edge
        Waka waka = new Waka(430, 200, null, 1, 3);
        waka.tick();
        assertArrayEquals(new int[] {429, 200}, waka.getCurrentCoordinates());
    }

    @Test
    void die() {
        // Ensure that Waka resets to the start when he dies and loses a life
        Waka waka = new Waka(430, 200, null, 1, 3);
        waka.die();
        assertEquals(2, waka.getLives());
        assertArrayEquals(new int[] {428, 188}, waka.getCurrentCoordinates());
    }

    @Test
    void collide() {
        // Ensure that the collisions work, and Waka dies
        Waka waka = new Waka(410, 200, null, 1, 3);
        Ambusher ambusher = new Ambusher(415, 205, null, 1);
        assertTrue(waka.collide(ambusher));
        assertEquals(2, waka.getLives());
        assertArrayEquals(new int[] {396, 188}, waka.getCurrentCoordinates());
    }

}
