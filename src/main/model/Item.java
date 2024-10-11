package model;

import java.util.Random;

/*
 * Represent a item with its position and effect(type:integer, value:integer)
 */

public class Item {
    private int x;
    private int y;
    private int type;
    private int value;

    //EFFECTS: construct an item with random effect
    public Item() {
        Random random = new Random();
        int type = random.nextInt(2); // 2 types: health or attack
        int value = random.nextInt(11); // 0 - 10

    }

    //MODIFIES: this
    //EFFECTS: item gets a position where it is dropped
    public void droppedAt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Getter
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


}
