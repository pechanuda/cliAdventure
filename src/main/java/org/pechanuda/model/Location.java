package org.pechanuda.model;

import java.util.ArrayList;
import java.util.List;

public class Location extends Entity {

    private Item key;
    private boolean locked;
    private boolean hidden;
    private List<Location> exits;
    private List<Item> items = new ArrayList<>();
    private List<Monster> monsters;


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
        this.items.addAll(items);
    }

    public Location(String name) {
        super(name);
    }
    public Location() {
        super();
    }

    public Item getKey() {
        return key;
    }

    public void setKey(Item key) {
        this.key = key;
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
}
