package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*
 * Represent a demon survivor game with a player, list of enemies,
 * list of items
 */
public class SurvivorGame implements Writable {
    private Player player;
    private List<Enemy> enemies;
    private List<Item> items;

    //EFFECTS: construct a survivor game with a player at (0,0), a enemy at random pos within (0-5,0-5) and a item
    public SurvivorGame() {
        player = new Player(0, 0);
        enemies = new ArrayList<Enemy>();
        items = new ArrayList<Item>();
        Random random = new Random();
        Enemy enemy = new Enemy(random.nextInt(5), random.nextInt(5));
        Item item = new Item();
        this.enemies.add(enemy);
        this.items.add(item);
    }

    //EFFECTS: construct a survivor game with a given player, and empty list of enemies and items
    public SurvivorGame(Player p) {
        player = p;
        enemies = new ArrayList<Enemy>();
        items = new ArrayList<Item>();
    }

    // MODIFIES: this
    // EFFECTS: player use first item in inventory
    public void playerUseFirstItem() {
        if (player.getInventorySize() > 0) {
            player.useItem(0);
        } else {
            System.out.println("You have nothing!");
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
                System.out.println("Collected: " + item.getName());
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
                System.out.println(player.getAttack() + " damage dealt!");
                if (enemy.getHealth() <= 0) {
                    enemy.dropItem();
                    items.add(enemy.getItem());// drop item on enemy's death
                    iterator.remove();
                    System.out.println("Enemy Killed");
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: player moves up or down or left or right by 1
    public void playerMove(Scanner input) {
        System.out.println("Which way? up | down | left | right");
        String direction = input.next();
        if (direction.equals("up")) {
            this.player.move(1);
        } else if (direction.equals("right")) {
            this.player.move(2);
        } else if (direction.equals("down")) {
            this.player.move(3);
        } else if (direction.equals("left")) {
            this.player.move(4);
        }

        System.out.println("Moved to (" + player.getPosX() + "," + player.getPosY() + ")");
    }

    //Setter & Getter
    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Item> getiItems() {
        return items;
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
