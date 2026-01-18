package org.pechanuda.model;

import java.util.List;

public class Location {

    private String name;
    private Item key;
    private boolean locked;
    private boolean hidden;
    private List<Location> exits;
    private List<Item> items;
    private List<Monster> monsters;
    private List<NPC> npcs;


    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Location(String name) {
        this.name = name;
    }

    public Item getKey() {
        return key;
    }

    public void setKey(Item key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
