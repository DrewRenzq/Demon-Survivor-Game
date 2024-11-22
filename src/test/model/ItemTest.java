package model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    private Item potion;
    private Item amulet;
    private Enemy demon;

    private Item heal;
    private Item maxHealth;
    private Item attack;

    @BeforeEach
    void runBefore() {
        potion = new Item();
        amulet = new Item();
        demon = new Enemy(1, 2);

        heal = new Item("heal",0,0);
        maxHealth = new Item("maxHealth",1,0);
        attack = new Item("attack",2,0);
        
    }

    @Test
    void testConstructor() {
        String[] names = potion.getNames();
        assertTrue(java.util.Arrays.asList(names).contains(potion.getName()));

        // assertTrue(potion.getType() >= 0 && potion.getType() <= 2);
        // assertTrue(potion.getValue() >= 0 && potion.getValue() <= 10);

        assertTrue(java.util.Arrays.asList(names).contains(amulet.getName()));
        // assertTrue(amulet.getType() >= 0 && amulet.getType() <= 2);
        // assertTrue(amulet.getValue() >= 0 && amulet.getValue() <= 10);

        assertNotEquals(potion, amulet);
    }

    @Test
    void testGetEffect() {
        assertEquals("Heal", heal.getEffect());
        assertEquals("Increase max health", maxHealth.getEffect());
        assertEquals("Increase attack", attack.getEffect());
    }

    @Test
    void testDroppedAt() {
        demon.setItem(potion);
        demon.dropItem();
        assertEquals(1, potion.getPosX());
        assertEquals(2, potion.getPosY());

        potion.droppedAt(3, 3);
        assertEquals(3, potion.getPosX());
        assertEquals(3, potion.getPosY());
        
    }

}
