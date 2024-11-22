package ui;

import javax.swing.*;
import java.awt.*;

import model.*;

/*
 * Represent A GamePanel which render the survivor game in grids
 */
public class GamePanel extends JPanel {
    private SurvivorGame game;

    // Constructs a game panel
    //Effect: render survivor game with preferred size and white background
    public GamePanel(SurvivorGame game) {
        setPreferredSize(new Dimension(SurvivorGame.WIDTH, SurvivorGame.HEIGHT));
        setBackground(Color.WHITE);
        this.game = game;
        setFocusable(false);
    }

    //Effect: repaint game according to update
    public void updateGame(SurvivorGame newGame) {
        this.game = newGame;
        repaint();
    }

    //super implementation
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int gridSize = 20;
        int cellSize = SurvivorGame.HEIGHT / gridSize;

        drawGame(g, gridSize, cellSize);
    }

    // Draws the game
	// modifies: g
	// effects:  draws the game onto g
    private void drawGame(Graphics g, int gridSize, int cellSize) {

        drawGrid(g, gridSize, cellSize);

        drawPlayer(g, cellSize);


        drawEnemies(g, cellSize);


        drawItems(g, cellSize);

        drawInventory(g);
    }

    // Draws the Inventory
	// modifies: g
	// effects:  draws the Inventory onto g
    private void drawInventory(Graphics g) {
        Player player = game.getPlayer();
        if (SurvivorGame.SHOWINFO) {
            g.setColor(new Color(50, 50, 50, 200));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            g.drawString("== Player Information ==", 10, 40);
            g.drawString("Health: " + player.getHealth() + " / " + player.getMaxHealth(), 20, 60);
            g.drawString("Attack: " + player.getAttack(), 20, 80);
            g.drawString("Inventory:", 20, 100);

            int y = 120;
            for (Item item : player.getInventory()) {
                g.drawString(item.getName() + " : " + item.getEffect() + " by " + item.getValue(), 30, y);
                y += 20;
            }
        }
    }

    // Draws the Items
	// modifies: g
	// effects:  draws the Items onto g     
    private void drawItems(Graphics g, int cellSize) {
        g.setColor(Color.GREEN);
        for (Item item : game.getItems()) {
            g.fillRect(item.getPosX() * cellSize + 10, item.getPosY() * cellSize + 10, cellSize - 20, cellSize - 20);
        }
    }

    // Draws the Enemies
	// modifies: g
	// effects:  draws the Enemies onto g
    private void drawEnemies(Graphics g, int cellSize) {
        g.setColor(Color.RED);
        for (Enemy enemy : game.getEnemies()) {
            g.fillRect(enemy.getPosX() * cellSize, enemy.getPosY() * cellSize, cellSize, cellSize);
        }
    }

    // Draws the Player
	// modifies: g
	// effects:  draws the Player onto g
    private void drawPlayer(Graphics g, int cellSize) {
        Player player = game.getPlayer();
        int playerX = player.getPosX() * cellSize;
        int playerY = player.getPosY() * cellSize;

        g.setColor(Color.BLACK);
        Polygon playerTriangle = new Polygon(
                new int[]{playerX + cellSize / 2, playerX, playerX + cellSize},
                new int[]{playerY, playerY + cellSize, playerY + cellSize},
                3
        );
        g.fillPolygon(playerTriangle);
    }

    // Draws the Grid
	// modifies: g
	// effects:  draws the Grid onto g
    private void drawGrid(Graphics g, int gridSize, int cellSize) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= gridSize; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, getHeight());
            g.drawLine(0, i * cellSize, getWidth(), i * cellSize);
        }
    }
}
