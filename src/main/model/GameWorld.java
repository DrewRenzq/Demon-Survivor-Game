package model;

/*
 * Represent a demon survivor game with a player, list of enemies,
 * list of items, and grid size(x by y)
 */

public class GameWorld {

    //EFFECTS: construct a gameworld with no enemy or item, a size of colum x row,
    //and player at 0,0
    public GameWorld(int colum, int row) {

    }

    //MODIFIES: this
    //EFFECTS: update player, enemies and items
    public void update() {

    }

    //MODIFIES: this
    //EFFECTS: move enemies towards player
    public void moveEnemiesToPlayer() {

    }

    //EFFECTS: check if the game is over
    public void checkGameOver() {

    }

    //EFFECTS: check if enemies and player on same position
    public void checkEnemiesPlayerPosition() {

    }

    //MODIFIES: this
    //EFFECTS: remove enemies that has health <= 0
    public void checkEnemies() {

    }

    //MODIFIES: this
    //EFFECTS: remove items picked up by player
    public void checkItems() {

    }


}
