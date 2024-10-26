package peresistence;

import model.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPlayer(int x, int y, int h, int mh, int a, List<Item> inventory, Player player) {
        assertEquals(x, player.getPosX());
        assertEquals(y, player.getPosY());
        assertEquals(h, player.getHealth());
        assertEquals(mh, player.getMaxHealth());
        assertEquals(a, player.getAttack());
        int index = 0;
        for (Item i : inventory) {
            assertEquals(i, player.getInventory().get(index));
            index += 1;
        }
    }

    // protected void checkEnemy(int x, int y, int h, int a, Item item, Enemy enemy) {
    //     assertEquals(x, enemy.getPosX());
    //     assertEquals(y, enemy.getPosY());
    //     assertEquals(h, enemy.getHealth());
    //     assertEquals(a, enemy.getAttack());
    //     assertEquals(item, enemy.getItem());
    // }

    // protected void checkItem(int x, int y, String name, int type, int value, Item item) {
    //     assertEquals(x, item.getPosX());
    //     assertEquals(y, item.getPosY());
    //     assertEquals(name, item.getName());
    //     assertEquals(type, item.getType());
    //     assertEquals(value, item.getValue());
    // }
}
