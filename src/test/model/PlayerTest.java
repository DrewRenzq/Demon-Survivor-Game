package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.event.KeyEvent;


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
        slayer.move(KeyEvent.VK_D);
        assertEquals(1,slayer.getPosX());
        assertEquals(0,slayer.getPosY());
        //move down
        slayer.move(KeyEvent.VK_S);
        assertEquals(1,slayer.getPosX());
        assertEquals(1,slayer.getPosY());
        //move up
        slayer.move(KeyEvent.VK_W);
        assertEquals(1,slayer.getPosX());
        assertEquals(0,slayer.getPosY());
        //move left
        slayer.move(KeyEvent.VK_A);
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
        slayer.collect(heal);
        slayer.useItem(0);
        assertEquals(10, slayer.getHealth());

        slayer.collect(helmet);
        slayer.useItem(0);
        assertEquals(15, slayer.getMaxHealth());

        slayer.collect(sword);
        slayer.useItem(0);
        assertEquals(16,slayer.getAttack());
        
    }
}
