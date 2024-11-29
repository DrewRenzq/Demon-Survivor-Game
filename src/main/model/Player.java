package model;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a player in game that has
 * a position, inventory, health
 * and different states (max health, defense, attack)
 */

public class Player implements Writable {
    private static final int BASE_STATES = 10;

    private int posX;
    private int posY;
    private int health;
    private int maxHealth;
    private int attack;
    private List<Item> inventory;

    // REQUIRES: x && y >= 0
    // EFFECTS: constructs a player with full health, basic states,
    // on position (x,y) and an empty inventory
    public Player(int x, int y) {
        this.posX = x;
        this.posY = y;
        maxHealth = BASE_STATES;
        health = maxHealth;
        attack = BASE_STATES;
        inventory = new ArrayList<Item>();
    }

    // EFFECTS: construct a player at 0,0, full health, empty inventory
    public Player() {
        posX = 0;
        posY = 0;
        maxHealth = BASE_STATES;
        health = maxHealth;
        attack = BASE_STATES;
        inventory = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: change player's position
    public void move(int direction) {
        if (direction == KeyEvent.VK_W) {
            this.posY -= 1;
        } else if (direction == KeyEvent.VK_D) {
            this.posX += 1;
        } else if (direction == KeyEvent.VK_S) {
            this.posY += 1;
        } else if (direction == KeyEvent.VK_A) {
            this.posX -= 1;
        }

        //handleBoundary();
    }


    // MODIFIES: this, EventLog
    // EFFECTS: add an item to player's inventory, add event into event log
    public void collect(Item item) {
        this.inventory.add(item);
        //Log
        EventLog.getInstance().logEvent(new Event("Item " + item.getName() + " collected."));
    }

    // MODIFIES: this, EventLog
    // EFFECTS: use an item in inventory to change states, after use item gone,
    //          add event into event log
    public void useItem(int index) {
        Item item = this.inventory.get(index);
        int type = item.getType();
        int value = item.getValue();
        if (type == 0) {
            this.health = maxHealth;
        } else if (type == 1) {
            this.maxHealth += value;
        } else {
            this.attack += value;
        }
        this.inventory.remove(index);
        //Log
        EventLog.getInstance().logEvent(new Event("Item " + item.getName() + " used."));
    }


    // Setter and Getter

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }


    public int getInventorySize() {
        return this.inventory.size();
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public void setMaxHealth(int h) {
        this.maxHealth = h;
    }

    public void setAttack(int a) {
        this.attack = a;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    // EFFECTS: produce a JSONObject to represent a Player object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("posX", posX);
        json.put("posY", posY);
        json.put("health", health);
        json.put("maxHealth", maxHealth);
        json.put("attack", attack);
        json.put("inventory", itemsToJson());
        return json;
    }

    // EFFECTS: returns items as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item t : inventory) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
