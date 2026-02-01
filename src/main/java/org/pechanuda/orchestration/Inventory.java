package org.pechanuda.orchestration;

import java.util.ArrayList;
import java.util.List;

import org.pechanuda.model.Item;

public class Inventory {
    private int maxCapacity;
    private int currentCapacity;

    private List<Item> items = new ArrayList<>();
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
