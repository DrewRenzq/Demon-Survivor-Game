package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemyTest {
    private Enemy demon;
    private Enemy golbin;
    private Player slayer;

    @BeforeEach
    void runBefore() {
        demon = new Enemy(0, 0);
        golbin = new Enemy(3,3);
        slayer = new Player(2,2);
    }

    @Test
    void testMoveToPlayer() {
        //demon
        demon.moveToPlayer(slayer);
        assertEquals(1, demon.getPosX());
        assertEquals(1, demon.getPosY());
        demon.moveToPlayer(slayer);
        assertEquals(2, demon.getPosX());
        assertEquals(2, demon.getPosY());
        demon.moveToPlayer(slayer);
        assertEquals(2, demon.getPosX());
        assertEquals(2, demon.getPosY());

        //golbin
        golbin.moveToPlayer(slayer);
        assertEquals(2, golbin.getPosX());
        assertEquals(2, golbin.getPosY());
        golbin.moveToPlayer(slayer);
        assertEquals(2, golbin.getPosX());
        assertEquals(2, golbin.getPosY());
    }

    @Test
    void testTakeDamage() {
        int damage = slayer.getAttack();
        golbin.setHealth(15);

        demon.takeDamage(damage);
        assertEquals(0, demon.getHealth());

        golbin.takeDamage(damage);
        assertEquals(5, golbin.getHealth());
        golbin.takeDamage(damage);
        assertEquals(0, golbin.getHealth());
    }

    @Test
    void testDropItem() {
        Item potion = demon.getItem();
        Item helmet = golbin.getItem();

        demon.dropItem();
        assertEquals(0, potion.getPosX());
        assertEquals(0, potion.getPosY());
        
        demon.moveToPlayer(slayer);
        demon.setItem(potion);
        demon.dropItem();
        assertEquals(1, potion.getPosX());
        assertEquals(1, potion.getPosY());

        golbin.dropItem();
        assertEquals(3, helmet.getPosX());
        assertEquals(3, helmet.getPosY());
    }
}
