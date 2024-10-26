package peresistence;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            SurvivorGame sg = new SurvivorGame();
            JsonWriter writer = new JsonWriter("./data/sg\1absurd:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterInitStateGame() {
        try {
            SurvivorGame sg = new SurvivorGame();
            JsonWriter writer = new JsonWriter("./data/testWriterInitStateGame.json");
            writer.open();
            writer.write(sg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterInitStateGame.json");
            sg = reader.read();
            assertEquals(0, sg.getEnemiesSize());
            assertEquals(0, sg.getItemsSize());
            List<Item> items = sg.getPlayerInventory();
            Player player = sg.getPlayer();
            checkPlayer(0, 0, 10, 10, 10, items, player);
            sg = reader.read();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    } 

    @Test
    @SuppressWarnings("methodlength")
    void testWriterSavedGame() {
        
        try {
            SurvivorGame sg = new SurvivorGame();
            Enemy enemy = new Enemy(4, 1);
            enemy.setAttack(1);
            enemy.setHealth(1);
            Item itema = new Item(0,0,"Armor",2,9);
            enemy.setItem(itema);
            sg.addEnemy(enemy);
            Item itemb = new Item(0,0,"Ring",1,7);
            sg.addItem(itemb);
            sg.getPlayer().collect(itemb);
            JsonWriter writer = new JsonWriter("./data/testWriterSavedGame.json");
            writer.open();
            writer.write(sg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSavedGame.json");
            sg = reader.read();
            assertEquals(1, sg.getEnemiesSize());
            assertEquals(1, sg.getItemsSize());
            List<Item> items = sg.getPlayerInventory();
            Player player = sg.getPlayer();
            checkPlayer(0, 0, 10, 10, 10, items, player);
            
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
