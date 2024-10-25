package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player slayer;
    private Item potion;
    private Item heal;
    private Item helmet;
    private Item sword;
    
    @BeforeEach
    void runBefore() {
        slayer = new Player();
        potion = new Item();
        heal = new Item("heal",0,0);
        helmet = new Item("helmet",1,5);
        sword = new Item("sword",2,6);

    }

    @Test
    void testMove() {
        assertEquals(0,slayer.getPosX());
        assertEquals(0,slayer.getPosY());
        //move right
        slayer.move(2);
        assertEquals(1,slayer.getPosX());
        assertEquals(0,slayer.getPosY());
        //move down
        slayer.move(3);
        assertEquals(1,slayer.getPosX());
        assertEquals(1,slayer.getPosY());
        //move up
        slayer.move(1);
        assertEquals(1,slayer.getPosX());
        assertEquals(0,slayer.getPosY());
        //move left
        slayer.move(4);
        assertEquals(0,slayer.getPosX());
        assertEquals(0,slayer.getPosY());
    }

    @Test
    void testCollect() {
        assertEquals(0,slayer.getInventorySize());
        slayer.collect(potion);
        assertEquals(1, slayer.getInventorySize());
        slayer.collect(helmet);
        assertEquals(2,slayer.getInventorySize());
        slayer.collect(sword);
        assertEquals(3,slayer.getInventorySize());
    }

    @Test
    void testUseItem() {
        assertEquals(10, slayer.getHealth());
        assertEquals(10, slayer.getMaxHealth());
        assertEquals(10, slayer.getAttack());


        slayer.setHealth(5);
        slayer.useItem(heal);
        assertEquals(10, slayer.getHealth());

        slayer.useItem(helmet);
        assertEquals(15, slayer.getMaxHealth());

        slayer.useItem(sword);
        assertEquals(16,slayer.getAttack());
        
    }
}
