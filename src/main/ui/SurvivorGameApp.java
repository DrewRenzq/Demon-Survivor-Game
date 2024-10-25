package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;


/*
 * Represent a demon survivor game application
 */

public class SurvivorGameApp {
    private static final String JSON_STORE = "./data/SurvivorGame.json";
    private SurvivorGame survivorGame;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: run the game
    public SurvivorGameApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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

    // MODIFIES: this
    // EFFECTS: initializes the game
    public void init() {
        survivorGame = new SurvivorGame();
        input = new Scanner(System.in);

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {
        if (command.equals("move")) {
            survivorGame.playerMove(input);
        } else if (command.equals("attack")) {
            survivorGame.playerAttack();
        } else if (command.equals("collect")) {
            survivorGame.playerCollect();
        } else if (command.equals("use")) {
            survivorGame.playerUseFirstItem();// first item used
        } else if (command.equals("save")) {
            saveWorkRoom();
        } else if (command.equals("load")) {
            loadWorkRoom();
        } else {
            System.out.println("You can't do that.");
        }
    }

   

    // EFFECTS: display all the information about the game, and command menu
    public void displayGame() {
        // Display Player Information
        displayPlayer();

        // Display Enemy Information
        displayEnemies();

        // Display Item Information
        displayItems();

        // Display menu
        displayMenu();

    }

    // EFFECTS: display Menu for actions
    public void displayMenu() {
        System.out.println("\n=== Action ===");
        System.out.println("\t> move");
        System.out.println("\t> attack");
        System.out.println("\t> collect");
        System.out.println("\t> use");
        System.out.println("\t> save");
        System.out.println("\t> load");
    }

    // EFFECTS: display items' information, name and location
    public void displayItems() {
        List<Item> items = survivorGame.getiItems();
        System.out.println("\n=== Items ===");
        if (items.isEmpty()) {
            System.out.println("No items.");
        } else {
            for (Item i : items) {
                System.out.println("Item: " + i.getName() + " at (" + i.getPosX() + "," + i.getPosY() + ")");
            }
        }
    }

    // EFFCTS: display enemis' information, location and health
    public void displayEnemies() {
        List<Enemy> enemies = survivorGame.getEnemies();
        System.out.println("\n=== Enemy Information ===");
        if (survivorGame.getEnemies().isEmpty()) {
            System.out.println("No enemies, chill.");
        } else {
            for (Enemy e : enemies) {
                System.out.println("Enemy at (" + e.getPosX() + "," + e.getPosY()
                        + ") Health: (" + e.getHealth() + ")");
            }
        }
    }

    // EFFECTS: display player information, postion, health, attack ,inventory list
    public void displayPlayer() {
        Player player = survivorGame.getPlayer();
        System.out.println("\n=== Player Information ===");
        System.out.println("Position: (" + player.getPosX() + ", " + player.getPosY() + ")");
        System.out.println("Health: " + player.getHealth() + " / " + player.getMaxHealth());
        System.out.println("Attack: " + player.getAttack());
        System.out.print("Inventory:  ");
        displayInventory();
    }

    // EFFECTS: display the players inventory
    public void displayInventory() {
        List<Item> inventory = survivorGame.getPlayer().getInventory();
        System.out.println();
        for (Item i : inventory) {
            System.out.print(i.getName() + " ");
        }
    }

    // // EFFECTS: saves the survivor game to file
    // private void saveWorkRoom() {
    //     try {
    //         jsonWriter.open();
    //         jsonWriter.write(survivorGame);
    //         jsonWriter.close();
    //         System.out.println("Saved current game to " + JSON_STORE);
    //     } catch (FileNotFoundException e) {
    //         System.out.println("Unable to write to file: " + JSON_STORE);
    //     }
    // }

    
    // // MODIFIES: this
    // // EFFECTS: loads workroom from file
    // private void loadWorkRoom() {
    //     try {
    //         survivorGame = jsonReader.read();
    //         System.out.println("Loaded game from " + JSON_STORE);
    //     } catch (IOException e) {
    //         System.out.println("Unable to read from file: " + JSON_STORE);
    //     }
    // }














    // MODIFIES: this
    // EFFECTS:
    public void update() {

    }

    // MODIFIES: this
    // EFFECTS: move enemies towards player
    public void moveEnemiesToPlayer() {

    }

    // EFFECTS: check if the game is over
    public void checkGameOver() {

    }

    // EFFECTS: check if enemies and player on same position
    public void checkEnemiesPlayerPosition() {

    }

    // MODIFIES: this
    // EFFECTS: remove enemies that has health <= 0
    public void checkEnemies() {

    }

    // MODIFIES: this
    // EFFECTS: remove items picked up by player
    public void checkItems() {

    }

}
