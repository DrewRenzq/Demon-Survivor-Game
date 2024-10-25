package model;

import java.util.ArrayList;
import java.util.List;


/*
 * Represents a player in game that has
 * a position, inventory, health
 * and different states (max health, defense, attack)
 */

public class Player {
    private static final int BASE_STATES = 10;

    private int posX;
    private int posY;
    private int health;
    private int maxHealth;
    private int attack;
    private List<Item> inventory;

    //REQUIRES: x && y >= 0
    //EFFECTS: constructs a player with full health, basic states, 
    //on position (x,y) and an empty inventory
    public Player(int x, int y) {
        this.posX = x;
        this.posY = y;
        maxHealth = BASE_STATES;
        health = maxHealth;
        attack = BASE_STATES;
        inventory = new ArrayList<Item>();
    }

    //EFFECTS: construct a player at 0,0, full health, empty inventory
    public Player() {
        posX = 0;
        posY = 0;
        maxHealth = BASE_STATES;
        health = maxHealth;
        attack = BASE_STATES;
        inventory = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: change player's position,
    //up == 1
    //right == 2
    //down == 3
    //left == 4
    public void move(int direction) {
        if (direction == 1) {
            this.posY -= 1;
        } else if (direction == 2) {
            this.posX += 1;
        } else if (direction == 3) {
            this.posY += 1;
        } else if (direction == 4) {
            this.posX -= 1;
        }
    }

    //MODIFIES: this
    //EFFECTS: add an item to player's inventory
    public void collect(Item item) {
        this.inventory.add(item);
    }

    //MODIFIES: this
    //EFFECTS: use an item in inventory to change states
    public void useItem(Item item) {
        int type = item.getType();
        int value = item.getValue();
        if (type == 0) {
            this.health = maxHealth;
        } else if (type == 1) {
            this.maxHealth += value;
        } else if (type == 2) {
            this.attack += value;
        }
    }

    //MODIFIES: this
    //EFFECTS: use an item to increase states

    //Setter and Getter

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

    public int getMaxHealth() {
        return this.maxHealth;
    }
}
