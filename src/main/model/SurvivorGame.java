package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.KeyEvent;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*
 * Represent a demon survivor game with a player, list of enemies,
 * list of items
 */
public class SurvivorGame implements Writable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static boolean SHOWINFO = false;
    private Player player;
    private List<Enemy> enemies;
    private List<Item> items;
    private boolean isGameOver;

    //EFFECTS: construct a survivor game with a player at (10,10), a enemy at random pos within (0-20,0-20)
    public SurvivorGame() {
        enemies = new ArrayList<Enemy>();
        items = new ArrayList<Item>();
        setUp();
    }

    //EFFECTS: construct a survivor game with a player, and empty enemies and items list
    public SurvivorGame(Player p) {
        enemies = new ArrayList<Enemy>();
        items = new ArrayList<Item>();
        player = p;
    }


    // Set the game
    // MODIFIES: this
    // EFFECTS: start the game with player in the center of the frame
    // and spawn random enemy
    private void setUp() {
        player = new Player(10, 10);
        isGameOver = false;

        Random random = new Random();
        // Add enemies
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            addEnemy(new Enemy(x, y));
        }
    }

    // MODIFIES: this
    // EFFECTS: update game on clock tick
    public void update() {
        spawnEnemy();

    }

    // MODIFIES: this
    // EFFECTS: spawn more enemies
    private void spawnEnemy() {

    }
    // Responds to key press codes
    // MODIFIES: this
    // EFFECTS: control player to move, fight, collect and use item in response
    // to given key pressed
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            player.move(keyCode);
        } else if (keyCode == KeyEvent.VK_S) {
            player.move(keyCode);
        } else if (keyCode == KeyEvent.VK_A) {
            player.move(keyCode);
        } else if (keyCode == KeyEvent.VK_D) {
            player.move(keyCode);
        } else if (keyCode == KeyEvent.VK_F) {
            playerAttack();
        } else if (keyCode == KeyEvent.VK_R) {
            playerCollect();
        } else if (keyCode == KeyEvent.VK_E) {
            playerUseFirstItem();
        } else if (keyCode == KeyEvent.VK_Q) {
            ifShowInfo();
        }
    }   

    // MODIFIES: this
    // EFFECTS: player use first item in inventory
    public void playerUseFirstItem() {
        if (player.getInventorySize() > 0) {
            player.useItem(0);
        } else {
        }

    }


    // MODIFIES: this
    // EFFECTS: player collect an item to inventory, remove collected item in items
    public void playerCollect() {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if ((item.getPosX() == player.getPosX()) && (item.getPosY() == player.getPosY())) {
                player.collect(item);
                iterator.remove();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: player attack, if killed enemy, remove enemycoc
    public void playerAttack() {
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if ((enemy.getPosX() == player.getPosX()) && (enemy.getPosY() == player.getPosY())) {
                enemy.takeDamage(player.getAttack());
                if (enemy.getHealth() <= 0) {
                    enemy.dropItem();
                    items.add(enemy.getItem());// drop item on enemy's death
                    iterator.remove();

                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: change whether to show information of player or not
    public void ifShowInfo() {
        SHOWINFO = !SHOWINFO;
    }

    //MODIFIES: this
    //EFFECTS: add a enemy to enemies
    public void addEnemy(Enemy e) {
        this.enemies.add(e);
    }

    //MODIFIES: this
    //EFFECTS: add a item to enemies
    public void addItem(Item i) {
        this.items.add(i);
    }

    //Setter & Getter
    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getEnemiesSize() {
        return enemies.size();
    }

    public int getItemsSize() {
        return items.size();
    }

    public List<Item> getPlayerInventory() {
        return player.getInventory();
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    //EFFECTS: produce a JSONObject to represent a Survivor Game object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player", player.toJson());
        json.put("enemies", enemiesToJson());
        json.put("items", itemsToJson());

        return json;
    }

    // EFFECTS: returns enemies as a JSON array
    private JSONArray enemiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Enemy e : enemies) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns items as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : items) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    
}
