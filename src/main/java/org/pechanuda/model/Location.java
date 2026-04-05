package org.pechanuda.model;

import java.util.ArrayList;
import java.util.List;

public class Location extends Entity {

    private Item key;
    private boolean isLocked;
    private boolean isHidden;
    private List<Location> exits = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();

    public Location(String name) {
        super(name);
    }

    public Location(int id, String name, boolean isLocked, boolean isHidden) {
        super(id, name);
        this.isLocked = isLocked;
        this.isHidden = isHidden;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        this.isLocked = locked;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        this.isHidden = hidden;
    }

    public void setItems(List<Item> items) {
        this.items.addAll(items);
    }

    public Item getKey() {
        return key;
    }

    public void setKey(Item key) {
        this.key = key;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Location> getExits() {
        return exits;
    }

    public void setExits(List<Location> exits) {
        this.exits = exits;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addExit(Location exit) {
        exits.add(exit);
    }
}
