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
        slayer = new Player (2,2);
    }

    @Test
    void testMoveToPlayer() {
        //demon
        demon.moveToPlayer(slayer);
        assertEquals(1, demon.getX());
        assertEquals(1, demon.getY());
        demon.moveToPlayer(slayer);
        assertEquals(2, demon.getX());
        assertEquals(2, demon.getY());
        demon.moveToPlayer(slayer);
        assertEquals(2, demon.getX());
        assertEquals(2, demon.getY());

        //golbin
        golbin.moveToPlayer(slayer);
        assertEquals(2, golbin.getX());
        assertEquals(2, golbin.getY());
        golbin.moveToPlayer(slayer);
        assertEquals(2, golbin.getX());
        assertEquals(2, golbin.getY());
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
        assertEquals(0, potion.getX());
        assertEquals(0, potion.getY());
        
        demon.moveToPlayer(slayer);
        demon.setItem(potion);
        demon.dropItem();
        assertEquals(1, potion.getX());
        assertEquals(1, potion.getY());

        golbin.dropItem();
        assertEquals(3, helmet.getX());
        assertEquals(3, helmet.getY());
    }
}
