package ghost.objects;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameMapTests {
    @Test
    void getters() {
        // Because nothing is initialized in the constructor, most of the getters should return null
        GameMap map = new GameMap();
        assertNull(map.getAmbusherStartingCoordinates());
        assertNull(map.getChaserStartingCoordinates());
        assertNull(map.getPlayerStartingCoordinates());
        assertNull(map.getIgnorantStartingCoordinates());
        assertNull(map.getWhimStartingCoordinates());
        assertFalse(map.isGameOver());
        assertEquals(3, map.getPlayerLives());
        assertEquals(1, map.getMovementSpeed());
    }
}
