package persistence;

import model.Enemy;
import model.Item;
import model.Player;
import model.SurvivorGame;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/*
 * Represents a reader that reads survivorgame from JSON data stored in file
 */
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads survivorgame from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SurvivorGame read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSurvivorGame(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses survivorgame from JSON object and returns it
    private SurvivorGame parseSurvivorGame(JSONObject jsonObject) {
        JSONObject playerObject = jsonObject.getJSONObject("player");
        Player player = parsePlayer(playerObject);
        SurvivorGame sg = new SurvivorGame(player);
        addEnemies(sg, jsonObject);
        addItems(sg, jsonObject);
        return sg;
    }

    //MODIFIES: sg
    //EFFECTS: parse items from JSON object and add to sg
    private void addItems(SurvivorGame sg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json: jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(sg, nextItem);
        }
    }



    //MODIFIES: sg
    //EFFECTS: parses enemies from JSON object and add to sg
    private void addEnemies(SurvivorGame sg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("enemies");
        for (Object json: jsonArray) {
            JSONObject nextEnemy = (JSONObject) json;
            addEnemy(sg, nextEnemy);
        }
    }

    //MODIFIES: sg
    //EFFECTS: parses enemu from JSON object and add to sg
    private void addEnemy(SurvivorGame sg, JSONObject jsonObject) {
        int posX = jsonObject.getInt("posX");
        int posY = jsonObject.getInt("posY");
        int health = jsonObject.getInt("health");
        int attack = jsonObject.getInt("attack");
        JSONObject itemJsonObject = jsonObject.getJSONObject("item");
        Item item = parseItem(itemJsonObject);
        Enemy enemy = new Enemy(posX,posY);
        enemy.setHealth(health);
        enemy.setAttack(attack);
        enemy.setItem(item);

        sg.addEnemy(enemy);
    }

    //EFFECTS: parses item from JSON object and returns it
    private Item parseItem(JSONObject jsonObject) {
        int posX = jsonObject.getInt("posX");
        int posY = jsonObject.getInt("posY");
        String name = jsonObject.getString("name");
        int type = jsonObject.getInt("type");
        int value = jsonObject.getInt("value");
        Item item = new Item(posX,posY,name,type,value);

        return item;
    }


    //EFFECTS: parses player from JSON object and returns it
    private Player parsePlayer(JSONObject playerObject) {
        int posX = playerObject.getInt("posX");
        int posY = playerObject.getInt("posY");
        Player player = new Player(posX, posY);
        int maxHealth = playerObject.getInt("maxHealth");
        player.setMaxHealth(maxHealth);
        int health = playerObject.getInt("health");
        player.setHealth(health);
        int attack = playerObject.getInt("attack");
        player.setAttack(attack);
        addInventory(player, playerObject);
        return player;
    }

    //MODIFIES: player
    //EFFECTS: parses items from JSON object and add them to player
    private void addInventory(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventory");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(player, nextItem);
        }
    }

    //MODIFIES: player
    //EFFECTS: parses item from JSON object and add them to player
    private void addItem(Player player, JSONObject jsonObject) {
        int posX = jsonObject.getInt("posX");
        int posY = jsonObject.getInt("posY");
        String name = jsonObject.getString("name");
        int type = jsonObject.getInt("type");
        int value = jsonObject.getInt("value");
        Item item = new Item(posX,posY,name,type,value);
        player.collect(item);
    }    
    
    //MODIFIES: sg
    //EFFECTS: parse item from JSON object and add to sg
    private void addItem(SurvivorGame sg, JSONObject jsonObject) {
        int posX = jsonObject.getInt("posX");
        int posY = jsonObject.getInt("posY");
        String name = jsonObject.getString("name");
        int type = jsonObject.getInt("type");
        int value = jsonObject.getInt("value");
        Item item = new Item(posX,posY,name,type,value);

        sg.addItem(item);
    }
}
