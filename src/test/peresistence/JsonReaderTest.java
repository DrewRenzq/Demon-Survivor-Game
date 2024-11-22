package peresistence;

import model.*;
import persistence.JsonReader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistingFile.json");
        try {
            SurvivorGame sg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderInitStateGameFile() {
        JsonReader reader = new JsonReader("./data/testReaderInitStateGame.json");
        try {
            SurvivorGame sg = reader.read();
            assertEquals(5, sg.getEnemiesSize());
            assertEquals(0, sg.getItemsSize());
            List<Item> items = sg.getPlayerInventory();
            Player player = sg.getPlayer();
            checkPlayer(0, 0, 10, 10, 10, items, player);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSavedGameFile() {
        JsonReader reader = new JsonReader("./data/testReaderSavedGame.json");
        try {
            SurvivorGame sg = reader.read();
            assertEquals(6, sg.getEnemiesSize());
            assertEquals(1, sg.getItemsSize());
            List<Item> items = sg.getPlayerInventory();
            Player player = sg.getPlayer();
            checkPlayer(1, 1, 10, 10, 10, items, player);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
