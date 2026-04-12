package org.pechanuda.orchestration;

import java.util.List;

import org.pechanuda.model.Item;
import org.pechanuda.model.Location;

public class GameState {

    private GameStatus gameStatus = GameStatus.IN_PROGRESS;
    private Location currentLocation;

    private Inventory inventory;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<Location> getAvailableLocations() {
        return currentLocation.getExits();
    }

    public void setCurrentLocationByName(String chosenLocation) {
        setCurrentLocation(getAvailableLocationByName(chosenLocation));
    }

    public Item getAvailableItemByName(String name) {
        for (Item item : currentLocation.getItems()) {
            if (item.getName().equals(name)) {
                currentLocation.getItems().remove(item);
                return item;
            }
        }
        throw new IllegalArgumentException("Unable to pick up item: " + name);
    }

    public Location getAvailableLocationByName(String name) {
        for (Location loc : currentLocation.getExits()) {
            if (loc.getName().equals(name)) {
                return loc;
            }
        }
        throw new IllegalArgumentException("Unable to find location: " + name);
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    public void initInventory() {
        inventory = new Inventory(10, 0);
    }

}
