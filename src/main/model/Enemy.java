package model;


/*
 * Represent an enemy in game, with
 * a position(x and y), health and carried item
 */

public class Enemy {
    private static final int BASE_STATES = 1;
    private int x;
    private int y;
    private int health;
    private int attack;
    private Item item;


    //Effects: constructs a enemy at position (x,y), hase health,
    //with random item carried
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        health = BASE_STATES;
        attack = BASE_STATES;
        item = new Item();
    }

    //EFFECTS: enemy move towards player, change position
    public void moveToPlayer(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();

        if (this.x < playerX) {
            this.x++;
        } else if (x > playerX) {
            this.x--;
        }

        if (this.y < playerY) {
            this.y++;
        } else if (this.y > playerY) {
            this.y--;
        }
    }

    //
    //EFFECTS: enemy health decreases when attacked
    public void takeDamage(int damage) {
        if (this.health >= damage) {
            this.health -= damage;
        } else if (this.health < damage) {
            this.health = 0;
        }
    }

    //EFFECTS: enemy drop the item (removed) it carries at position it dies
    public void dropItem() {
        item.droppedAt(this.x, this.y);
        this.item = null;
    }

    //Setter Getter

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttack() {
        return this.attack;
    }

    public Item getItem() {
        return this.item;
    }

    //For testing
    public void setItem(Item a) {
        this.item = a;
    }

    public void setHealth(int x) {
        this.health = x;
    }
}
