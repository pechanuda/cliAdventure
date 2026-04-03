package org.pechanuda.orchestration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.pechanuda.model.GameWorld;
import org.pechanuda.model.Item;
import org.pechanuda.model.Location;
import org.pechanuda.model.Monster;

public class WorldLoader {

    private GameWorld gameWorld;

    public GameWorld load(String filename) {

        gameWorld = new GameWorld();
        try {
            InputStream is = Files.newInputStream(Paths.get("src/main/resources/" + filename));

            JsonReader jsonReader = Json.createReader(is);

            JsonObject gameWordlJsonObject = jsonReader.readObject();

            gameWorld.setName(gameWordlJsonObject.getString("gameName"));
            gameWorld.setDisplayName(gameWordlJsonObject.getString("displayGameName"));
            parseItems(gameWordlJsonObject);
            parseMonsters(gameWordlJsonObject);
            parseLocations(gameWordlJsonObject);

        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + filename, e);
        }

        return gameWorld;
    }

    private void parseItems(JsonObject gameWordlJsonObject) {
        JsonArray itemsArr = gameWordlJsonObject.getJsonArray("items");
        List<Item> items = new ArrayList<>();

        for(JsonValue itemValue : itemsArr) {
            JsonObject locObject = itemValue.asJsonObject();
            Item item = new Item(
                    locObject.getInt("id"),
                    locObject.getString("name")
            );
            items.add(item);
        }

        gameWorld.setItems(items);
    }

    private void parseMonsters(JsonObject gameWordlJsonObject) {
        JsonArray monstersArr = gameWordlJsonObject.getJsonArray("monsters");
        List<Monster> monsters = new ArrayList<>();

        for(JsonValue monsterValue : monstersArr) {
            JsonObject locObject = monsterValue.asJsonObject();

            Item lootItem = null;
            try {
                lootItem = getLoadedItemById(locObject.getInt("lootId"));
            } catch (NullPointerException ignored) {
                System.out.println("NPE caught, loot item will be null");
            }
            Monster monster = new Monster(
                locObject.getInt("id"),
                locObject.getString("name"),
                locObject.getInt("hitPoints"),
                locObject.getInt("attack"),
                locObject.getInt("defense"),
                lootItem
            );
            monsters.add(monster);
        }

        gameWorld.setMonsters(monsters);
    }

    private void parseLocations(JsonObject gameWordlJsonObject) {
        JsonArray locationArr = gameWordlJsonObject.getJsonArray("locations");
        List<Location> locations = new ArrayList<>();

        for(JsonValue locationValue : locationArr) {
            JsonObject locObject = locationValue.asJsonObject();
            Location loc = new Location();

            loc.setName(locObject.getString("name"));
            loc.setId(locObject.getInt("id"));
            loc.setHidden(locObject.getBoolean("hidden"));
            loc.setLocked(locObject.getBoolean("locked"));

            List<JsonValue> itemsArr = locObject.getJsonArray("items").asJsonArray();
            for (JsonValue itemValue : itemsArr) {

                int itemId = Integer.parseInt(itemValue.toString());
                loc.getItems().add(gameWorld.getItemById(itemId));
            }

            locations.add(loc);
        }

        gameWorld.setLocations(locations);
    }

    public Item getLoadedItemById(int itemId) {
        for (Item item : gameWorld.getItems()) {
            if(item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public Monster getLoadedMonsterById(int monsterId) {
        for (Monster monster : gameWorld.getMonsters()) {
            if(monster.getId() == monsterId) {
                return monster;
            }
        }
        return null;
    }
}
