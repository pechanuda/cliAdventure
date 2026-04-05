package org.pechanuda.orchestration;

import org.pechanuda.model.Location;

public class GameState {

    Location currentLocation;

    Inventory inventory;

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
