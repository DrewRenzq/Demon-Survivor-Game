package model;

import org.json.JSONObject;
import persistence.Writable;
import java.util.Random;

/*
 * Represent a item with its position and effect(type:integer, value:integer)
 */

public class Item implements Writable {
    private int posX;
    private int posY;
    private String name;
    private int type;
    private int value;
    String[] possibleNames = {"Potion", "Sword", "Helmet","Armor","Amulet","Ring"};

    //EFFECTS: construct an item with a random name and effect
    public Item() {
        Random random = new Random();
        this.name = possibleNames [random.nextInt(possibleNames.length)];
        this.type = random.nextInt(3); // 0 - heal, 1 - increase max health, 2 - attack
        this.value = random.nextInt(11); // 0 - 10

    }

    //For testing, constructor with certain effect
    public Item(String name,int type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    //For testing, constructor with certain effect
    public Item(int x, int y, String name,int type, int value) {
        this.posX = x;
        this.posY = y;
        this.name = name;
        this.type = type;
        this.value = value;
    }

    //MODIFIES: this
    //EFFECTS: item gets a position where it is dropped
    public void droppedAt(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    //Getter Setter
    public int getType() {
        return this.type;
    }

    public int getValue() {
        return this.value;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }
    
    public String getName() {
        return this.name;
    }

    public String[] getNames() {
        return possibleNames;
    }

    //EFFECTS: produce a JSONObject to represent a Item object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("posX", posX);
        json.put("posY", posY);
        json.put("name", name);
        json.put("type", type);
        json.put("value", value);
        return json;
    }    
}
