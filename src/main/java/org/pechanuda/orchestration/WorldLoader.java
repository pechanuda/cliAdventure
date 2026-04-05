package org.pechanuda.orchestration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
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
            populateLocationExists(gameWordlJsonObject);

            Location initLocation = gameWorld.getLocationById(gameWordlJsonObject.getInt("initLocationId"));
            gameWorld.setInitLocation(initLocation);

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
                lootItem = gameWorld.getItemById(locObject.getInt("lootId"));
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
            Location loc = new Location(
                locObject.getInt("id"),
                locObject.getString("name"),
                locObject.getBoolean("hidden"),
                locObject.getBoolean("locked")
            );
            List<Integer> itemsIdsList = null;
            try {
                itemsIdsList = locObject.getJsonArray("items").getValuesAs(JsonNumber::intValue);
            } catch (NullPointerException ignored) {}
            if (itemsIdsList != null) {
                for (int itemId : itemsIdsList) {
                    loc.addItem(gameWorld.getItemById(itemId));
                }
            }

            List<Integer> monsterIdsList = null;
            try {
                monsterIdsList = locObject.getJsonArray("monsters").getValuesAs(JsonNumber::intValue);
            } catch (NullPointerException ignored) {}
            if (monsterIdsList != null) {
                for (int monsterId : monsterIdsList) {
                    if (loc.getMonsters() == null) {
                        loc.setMonsters(List.of(gameWorld.getMonsterById(monsterId)));
                    } else {
                        loc.addMonster(gameWorld.getMonsterById(monsterId));
                    }
                }
            }

            locations.add(loc);
        }

        gameWorld.setLocations(locations);
    }

    private void populateLocationExists(JsonObject gameWordlJsonObject) {
        JsonArray locationArr = gameWordlJsonObject.getJsonArray("locations");

        for(JsonValue locationValue : locationArr) {
            JsonObject locObject = locationValue.asJsonObject();
            int locId = locObject.getInt("id");
            List<Integer> exitsIdsList = null;
            try {
                exitsIdsList = locObject.getJsonArray("exits").getValuesAs(JsonNumber::intValue);
            } catch (NullPointerException ignored) {}
            Location loc = gameWorld.getLocationById(locId);
            if (exitsIdsList != null) {
                for (int exitId : exitsIdsList) {
                    loc.addExit(gameWorld.getLocationById(exitId));
                }
            }
        }
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
