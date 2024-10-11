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

    private int positionX;
    private int positionY;
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private List<Item> inventory;

    //REQUIRES: x && y >= 0
    //EFFECTS: constructs a player with full health, basic states, 
    //on position (x,y) and an empty inventory
    public Player (int x, int y) {
        //stud
    }

    //EFFECTS: construct a player at 0,0, full health, empty inventory
    public Player () {

    }

    //MODIFIES: this
    //EFFECTS: change player's position,
    //up == 1
    //right == 2
    //down == 3
    //left == 4
    public void move(int direction) {

    }

    //MODIFIES: this
    //EFFECTS: add an item to player's inventory
    public void collect(Item item) {
        //stud
    }



    //Setter and Getter

    public int getPositionX() {
        return this.positionX;
    }
    
    public int getPositionY() {
        return this.positionY;
    }

    public int getInventorySize() {
        return this.inventory.size();
    }
}
