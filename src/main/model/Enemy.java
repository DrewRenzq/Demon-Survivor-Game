package model;

import org.json.JSONObject;
import persistence.Writable;

/*
 * Represent an enemy in game, with
 * a position(x and y), health and carried item
 */

public class Enemy implements Writable {
    private static final int BASE_STATES = 10;
    private int posX;
    private int posY;
    private int health;
    private int attack;
    private Item item;


    //Effects: constructs a enemy at position (x,y), hase health,
    //with random item carried
    public Enemy(int x, int y) {
        this.posX = x;
        this.posY = y;
        health = BASE_STATES;
        attack = BASE_STATES;
        item = new Item();
    }

    //EFFECTS: enemy move towards player, change position
    public void moveToPlayer(Player player) {
        int playerX = player.getPosX();
        int playerY = player.getPosY();

        if (this.posX < playerX) {
            this.posX++;
        } else if (posX > playerX) {
            this.posX--;
        }

        if (this.posY < playerY) {
            this.posY++;
        } else if (this.posY > playerY) {
            this.posY--;
        }
    }

    //EFFECTS: enemy health decreases when attacked
    public void takeDamage(int damage) {
        if (this.health > damage) {
            this.health -= damage;
        } else {
            this.health = 0;
        } 
    }

    //EFFECTS: enemy drop the item (removed) it carries, at position it dies
    public void dropItem() {
        item.droppedAt(this.posX, this.posY);
    }

    //Setter Getter

    public int getPosX() {
        return this.posX;
    }
    
    public int getPosY() {
        return this.posY;
    }

    public int getHealth() {
        return this.health;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item a) {
        this.item = a;
    }

    public void setHealth(int x) {
        this.health = x;
    }

    public void setAttack(int x) {
        this.attack = x;
    }

    //EFFECTS: produce a JSONObject to represent a Enemy object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("posX", posX);
        json.put("posY", posY);
        json.put("health", health);
        json.put("attack", attack);
        json.put("item", item.toJson());
        return json;
    }
}
