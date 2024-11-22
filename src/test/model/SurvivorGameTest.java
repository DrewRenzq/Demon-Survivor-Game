package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class SurvivorGameTest {
    private SurvivorGame game;
    private Item heal;
    private Item helmet;
    private Item testItem;
    private Enemy enemy;
    private List<Enemy> testEnemies;

    @BeforeEach
    void runBefore() {
        game = new SurvivorGame();
        heal = new Item(0,0,"heal", 0, 0);
        helmet = new Item(11,11,"helmet", 1, 1);
        testItem = new Item(10,10,"testItem", 0, 0);
        enemy = new Enemy(10, 10);


        game.getItems().add(heal);
        game.getItems().add(helmet);
        game.getItems().add(testItem);
        game.getEnemies().add(enemy);
        SurvivorGame.SHOWINFO = false;
    }

    @Test
    void testPlayerUseFirstItem() {
        // Test using an item when inventory is not empty
        game.getPlayer().collect(heal);
        assertEquals(1, game.getPlayer().getInventorySize());
        game.playerUseFirstItem(); // Should use heal and potentially restore health
        assertEquals(0, game.getPlayer().getInventorySize()); // Item used and removed

        // Test attempting to use when inventory is empty
        game.playerUseFirstItem(); // Should trigger no-operation message
        assertEquals(0, game.getPlayer().getInventorySize()); // Still empty
    }

    @Test
    void testSetUp() {
        assertTrue(game.getEnemiesSize() > 0);
        assertTrue(game.getPlayer().getPosX() == 10);
        assertTrue(game.getPlayer().getPosY() == 10);
        assertTrue(game.getItemsSize() == 3);
        assertTrue(game.getIsGameOver() == false);
    }

    @Test
    void testIfShowInfo() {
        assertFalse(SurvivorGame.SHOWINFO);
        game.ifShowInfo();
        assertTrue(SurvivorGame.SHOWINFO);
        game.ifShowInfo();
        assertFalse(SurvivorGame.SHOWINFO);
    }

    @Test
    void testKeyPressed() {
        game.keyPressed(KeyEvent.VK_W);
        assertTrue(game.getPlayer().getPosY() == 9);
        game.keyPressed(KeyEvent.VK_S);
        assertTrue(game.getPlayer().getPosY() == 10);
        game.keyPressed(KeyEvent.VK_A);
        assertTrue(game.getPlayer().getPosX() == 9);
        game.keyPressed(KeyEvent.VK_D);
        assertTrue(game.getPlayer().getPosX() == 10);

        int size = game.getEnemiesSize();
        game.keyPressed(KeyEvent.VK_F);
        assertTrue(game.getEnemiesSize() == (size-1));
        game.keyPressed(KeyEvent.VK_R);
        assertEquals(2, game.getPlayer().getInventorySize());
        game.keyPressed(KeyEvent.VK_E);
        assertEquals(1, game.getPlayer().getInventorySize());
        game.keyPressed(KeyEvent.VK_Q);
        assertTrue(SurvivorGame.SHOWINFO);

        game.keyPressed(KeyEvent.VK_K);
        assertTrue(SurvivorGame.SHOWINFO);
    }

    @Test
    void testPlayerCollect() {
        assertEquals(0, game.getPlayer().getInventorySize());
        game.playerCollect(); // Player should collect heal
        assertEquals(1, game.getPlayer().getInventorySize());
        assertEquals(2, game.getItems().size()); // Only one item should remain

        // Move player to collect helmet
        game.playerCollect();
        assertEquals(1, game.getPlayer().getInventorySize());
        game.getPlayer().move(KeyEvent.VK_D);
        game.playerCollect();
        assertEquals(1, game.getPlayer().getInventorySize());

        game.getPlayer().move(KeyEvent.VK_A);
        game.getPlayer().move(KeyEvent.VK_S);
        assertEquals(1, game.getPlayer().getInventorySize());

        game.getPlayer().move(KeyEvent.VK_D);
        game.playerCollect();
        assertEquals(2, game.getPlayer().getInventorySize());
        assertEquals(1, game.getItems().size()); // No items left
    }

    @Test
    void testPlayerAttack() {
        // Set enemy health and player attack strength for controlled testing
        enemy.setHealth(20);
        game.getPlayer().setAttack(5);

        game.getPlayer().move(KeyEvent.VK_D);
        game.playerAttack();
        assertEquals(20, enemy.getHealth());

        game.getPlayer().move(KeyEvent.VK_S);
        game.playerAttack();
        assertEquals(20, enemy.getHealth());

        game.getPlayer().move(KeyEvent.VK_A);
        game.playerAttack();
        assertEquals(20, enemy.getHealth());

        game.getPlayer().move(KeyEvent.VK_W);
        game.playerAttack();
        
        // Verify the enemy health is reduced correctly
        assertEquals(15, enemy.getHealth());

        // Test attack again until enemy is defeated
        game.playerAttack();
        game.playerAttack();
        game.playerAttack();
        
        assertEquals(0, enemy.getHealth()); // Enemy should not have negative health
    }

}