package model;

import java.util.Random;

/*
 * Represent a item with its position and effect(type:integer, value:integer)
 */

public class Item {
    private int x;
    private int y;
    private String name;
    private int type;
    private int value;
    String[] possibleNames = {"Potion", "Sword", "Helmet","Armor","Amulet","Ring"};

    //EFFECTS: construct an item with a random name and effect
    public Item() {
        Random random = new Random();
        this.name = possibleNames [random.nextInt(possibleNames.length)];
        this.type = random.nextInt(3); // 3 types: heal, increase max health or attack
        this.value = random.nextInt(11); // 0 - 10

    }

    //MODIFIES: this
    //EFFECTS: item gets a position where it is dropped
    public void droppedAt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Getter Setter
    public int getType() {
        return this.type;
    }

    public int getValue() {
        return this.value;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public String getName() {
        return this.name;
    }

    public String[] getNames() {
        return possibleNames;
    }


}
