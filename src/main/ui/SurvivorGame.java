package ui;

import model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/*
 * Represent a demon survivor game with a player, list of enemies,
 * list of items, and grid size(x by y)
 */

public class SurvivorGame {
    private Player player;
    private List<Enemy> enemies;
    private List<Item> items;
    private Scanner input;

    //EFFECTS: run the game
    public SurvivorGame() {
        runGame();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runGame() {
        boolean gameGoing = true;
        String command = null;

        init();

        while (gameGoing) {
            displayGame();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                gameGoing = false;
            } else {
                processCommand(command);
                update();
            }
        }

        System.out.println("\nGame Over");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    public void processCommand(String command) {
        if(command.equals("move")) {
            playerMove();
        } else if (command.equals("attack")){
            playerAttack();
        } else if (command.equals("collect")) {
            playerCollect();
        } else if (command.equals("use")) {
            playerUseFirstItem();// first item used
        } else {
            System.out.println("You can't do that.");
        }
    }

    //MODIFIES: this
    //EFFECTS: player use first item in inventory
    public void playerUseFirstItem() {
        if (player.getInventorySize()>0) {
            player.useItem(player.getInventory().get(0));
        } else {
            System.out.println("You have nothing!");
        }

    }

    //MODIFIES: this
    //EFFECTS: player collect item on location 
    public void playerCollect() {
        for (Item i: items) {
            if ((i.getX()==player.getX()) && (i.getY()==player.getY())) {
                player.collect(i);
            }
        }
        System.out.println("Done collect.");
    }

    //MODIFIES: this
    //EFFECTS: player attack
    public void playerAttack() {
        for (Enemy e: enemies) {
            if ((e.getX()==player.getX()) && (e.getY()==player.getY())) {
                e.takeDamage(player.getAttack());
                System.out.println(player.getAttack()+" damage dealt!");
            } 
        }

        System.out.println("Done attack.");
    }

    //MODIFIES: this
    //EFFECTS: player moves
    public void playerMove() {
       System.out.println("Which way? up | down | left | right"); 
       String direction = input.next();
        if(direction.equals("up")) {
            this.player.move(1);
        } else if (direction.equals("right")) {
            this.player.move(2);
        } else if (direction.equals("down")) {
            this.player.move(3);
        } else if (direction.equals("left")) {
            this.player.move(4);
        }

        System.out.println("Moved to (" +player.getX()+","+player.getY()+")");
    }

    //EFFECTS: display all the information about the game, and command menu
    public void displayGame() {
        //Display Player Information
        displayPlayer();

        //Display Enemy Information
        displayEnemies();

        //Display Item Information
        displayItems();

        //Display menu
        displayMenu();

    }

    //EFFECTS: display Menu for actions
    public void displayMenu() {
        System.out.println("\n=== Action ===");
        System.out.println("\t> move");
        System.out.println("\t> attack");
        System.out.println("\t> collect");
        System.out.println("\t> use");
    }

    //EFFECTS: display items' information, name and location
    public void displayItems() {
        System.out.println("\n=== Items ===");
        if (items.isEmpty()) {
            System.out.println("No items.");
        } else {
            for (Item i : items) {
                System.out.println("Item: "+ i.getName() + " at (" + i.getX()+","+i.getY()+")");
            }
        }
    }

    //EFFCTS: display enemis' information, location and health
    public void displayEnemies() {
        System.out.println("\n=== Enemy Information ===");
        if (enemies.isEmpty()) {
            System.out.println("No enemies, chill.");
        } else {
            for (Enemy e: enemies) {
                System.out.println("Enemy at (" + e.getX()+","+e.getY()+ ") Health: ("+e.getHealth()+")");
            }
        }
    }

    //EFFECTS: display player information, postion, health, attack ,inventory list
    public void displayPlayer() {
        System.out.println("\n=== Player Information ===");
        System.out.println("Position: (" +  player.getX() + ", " + player.getY() + ")");
        System.out.println("Health: " + player.getHealth() +" / "+ player.getMaxHealth());
        System.out.println("Attack: " +player.getAttack());
        System.out.print("Inventory:  " );
        displayInventory();
    }

    //EFFECTS: display the players inventory
    public void displayInventory() {
        List<Item> inventory = player.getInventory();
        System.out.println();
        for (Item i : inventory) {
            System.out.print(i.getName());
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes the game
    public void init() {
        player = new Player(0, 0);
        enemies = new ArrayList<Enemy>();
        items = new ArrayList<Item>();
        input = new Scanner(System.in);
        Random random = new Random();
        Enemy enemy = new Enemy(random.nextInt(5), random.nextInt(5));
        Item item = new Item();
        this.enemies.add(enemy);
        this.items.add(item);
    }

    //MODIFIES: this
    //EFFECTS:
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
