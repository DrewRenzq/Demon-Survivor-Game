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

    //MODIFIES: this
    //EFFECTS: change player's position, moving up
    public void move(int direction) {

    }

    //MODIFIES: this
    //EFFECTS: add an item to player's inventory
    public void collect(Item item) {
        //stud
    }



    //Setter and Getter
    
}
