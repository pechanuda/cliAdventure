package org.pechanuda.model;

import java.util.List;

public class GameWorld {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Location getInitLocation() {
        return initLocation;
    }

    public void setInitLocation(Location initLocation) {
        this.initLocation = initLocation;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private String name;
    private String displayName;
    private Location initLocation;
    private List<Location> locations;
    private List<Monster> monsters;
    private List<Item> items;

    public Item getItemById(int itemId) {
        for (Item i : items) {
            if (i.getId() == itemId) {
                return i;
            }
        }
        throw new RuntimeException("Item with id: " + itemId + " not found.");
    }

    public Monster getMonsterById(int monsterId) {
        for (Monster m : monsters) {
            if (m.getId() == monsterId) {
                return m;
            }
        }
        throw new RuntimeException("Monster with id: " + monsterId + " not found.");
    }

    public Location getLocationById(int locationId) {
        for (Location loc : locations) {
            if (loc.getId() == locationId) {
                return loc;
            }
        }
        throw new RuntimeException("Location with id: " + locationId + " not found.");
    }
}
