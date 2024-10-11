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

    private int x;
    private int y;
    private int health;
    private int maxHealth;
    private int attack;
    private List<Item> inventory;

    //REQUIRES: x && y >= 0
    //EFFECTS: constructs a player with full health, basic states, 
    //on position (x,y) and an empty inventory
    public Player (int x, int y) {
        this.x = x;
        this.y = y;
        maxHealth = BASE_STATES;
        health = maxHealth;
        attack = BASE_STATES;
        inventory = new ArrayList<>();
    }

    //EFFECTS: construct a player at 0,0, full health, empty inventory
    public Player () {
        x = 0;
        y = 0;
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
            this.y -= 1;
        } else if (direction == 2) {
            this.x += 1;
        } else if (direction == 3) {
            this.y +=1;
        } else if (direction == 4) {
            this.x -= 1;
        }
    }

    //MODIFIES: this
    //EFFECTS: add an item to player's inventory
    public void collect(Item item) {
        this.inventory.add(item);
    }



    //Setter and Getter

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    public int getInventorySize() {
        return this.inventory.size();
    }

    public int getAttack() {
        return this.attack;
    }
}
