package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    private Item potion;
    private Enemy demon;

    @BeforeEach
    void runBefore() {
        potion = new Item();
        demon = new Enemy(1, 2);
    }

    @Test
    void testDroppedAt() {
        demon.setItem(potion);
        demon.dropItem();
        assertEquals(1, potion.getX());
        assertEquals(2, potion.getY());

        potion.droppedAt(3, 3);
        assertEquals(3, potion.getX());
        assertEquals(3, potion.getY());
        


        
    }

}
