package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player slayer;
    private Item potion;
    private Item helmet;
    
    @BeforeEach
    void runBefore() {
        slayer = new Player();
        potion = new Item();

    }

    @Test
    void testMove() {
        assertEquals(0,slayer.getX());
        assertEquals(0,slayer.getY());
        //move right
        slayer.move(2);
        assertEquals(1,slayer.getX());
        assertEquals(0,slayer.getY());
        //move down
        slayer.move(3);
        assertEquals(1,slayer.getX());
        assertEquals(1,slayer.getY());
        //move up
        slayer.move(1);
        assertEquals(1,slayer.getX());
        assertEquals(0,slayer.getY());
        //move left
        slayer.move(4);
        assertEquals(0,slayer.getX());
        assertEquals(0,slayer.getY());
    }

    @Test
    void testCollect() {
        assertEquals(0,slayer.getInventorySize());
        slayer.collect(potion);
        assertEquals(1, slayer.getInventorySize());
        slayer.collect(helmet);
        assertEquals(2,slayer.getInventorySize());
        slayer.collect(potion);
        assertEquals(3,slayer.getInventorySize());
    }
}
