package ui;

import javax.swing.*;
import java.awt.*;

import model.*;

// Game Panel with custom rendering
public class GamePanel extends JPanel {
    private SurvivorGame game;

    public GamePanel(SurvivorGame game) {
        setPreferredSize(new Dimension(SurvivorGame.WIDTH, SurvivorGame.HEIGHT));
        setBackground(Color.WHITE);
        this.game = game;
        setFocusable(false);
    }

    public void updateGame(SurvivorGame newGame) {
        this.game = newGame;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int gridSize = 20;
        int cellSize = SurvivorGame.HEIGHT / gridSize;

        // Draw grid
        drawGrid(g, gridSize, cellSize);

        // Draw player
        Player player = game.getPlayer();
        drawPlayer(g, cellSize, player);

        // Draw enemies
        drawEnemies(g, cellSize);

        // Draw items
        drawItems(g, cellSize);

        // Draw inventory
        drawInventory(g, player);
    }

    private void drawInventory(Graphics g, Player player) {
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

    private void drawItems(Graphics g, int cellSize) {
        g.setColor(Color.GREEN);
        for (Item item : game.getItems()) {
            g.fillRect(item.getPosX() * cellSize + 10, item.getPosY() * cellSize + 10, cellSize - 20, cellSize - 20);
        }
    }

    private void drawEnemies(Graphics g, int cellSize) {
        g.setColor(Color.RED);
        for (Enemy enemy : game.getEnemies()) {
            g.fillRect(enemy.getPosX() * cellSize, enemy.getPosY() * cellSize, cellSize, cellSize);
        }
    }

    private void drawPlayer(Graphics g, int cellSize, Player player) {
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

    private void drawGrid(Graphics g, int gridSize, int cellSize) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= gridSize; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, getHeight());
            g.drawLine(0, i * cellSize, getWidth(), i * cellSize);
        }
    }
}
