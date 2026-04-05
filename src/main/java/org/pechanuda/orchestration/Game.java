package org.pechanuda.orchestration;

import java.util.ArrayList;
import java.util.List;

import org.pechanuda.model.GameWorld;
import org.pechanuda.model.Item;
import org.pechanuda.model.Location;

public class Game implements IGame {

    private GameWorld gameWorld = null;
    private GameStatus gameStatus = GameStatus.IN_PROGRESS;
    private static Inventory inventory = new Inventory();

    private static List<Location> availableLocations = new ArrayList<>();
    private static Location currentLocation;

    public static Inventory getInventory() {
        return inventory;
    }

    public static void setInventory(Inventory inventory) {
        Game.inventory = inventory;
    }

    public List<Location> getLocations() {
        return availableLocations;
    }

    public static List<Location> getAvailableLocations() {
        return availableLocations;
    }

    public static void setAvailableLocations(List<Location> locations) {
        Game.availableLocations = locations;
    }

    public static Location getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(Location currentLocation) {
        Game.currentLocation = currentLocation;
    }

    public static void setCurrentLocationByName(String chosenLocation) {
        setCurrentLocation(getAvailableLocationByName(chosenLocation));
        setAvailableLocations(currentLocation.getExits());
    }

    public Game() {
    }


    @Override
    public void promptPlayer(GameState gameState) {
//        System.out.println("You are in: " + currentLocation);
//        System.out.println("Available exits are: " + currentLocation.getExits());
////        System.out.print("Available exits are: " + currentLocation.getExits().stream().filter(location -> location.isHidden() = false));
//        System.out.println("Available items are: " + currentLocation.getItems());
//        System.out.println("What do you want to do next?");
//        System.out.println("go <LOCATION> | attack <MONSTER> | pick <ITEM> | use <ITEM>");

        String promptResult = PromptResolution.readPrompt(gameState);

        if (promptResult.equalsIgnoreCase("exit")) {
            this.gameStatus = GameStatus.EXITED;
        }
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }


    @Override
    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void initGame(GameState gameState) {
        gameState.setCurrentLocation(gameWorld.getInitLocation());
        availableLocations = gameState.getCurrentLocation().getExits();
    }

    @Override
    public void printOutro() {
        if (getGameStatus().equals(GameStatus.VICTORY)) {
            System.out.println("Congratz!");
        } if (getGameStatus().equals(GameStatus.EXITED)) {
            System.out.println("Sorry to hear that you're leaving, see you next time!");
        } else {
            System.out.println("gg");
        }
    }

    public static Item getAvailableItemByName(String name) {
        for (Item item : currentLocation.getItems()) {
            if (item.getName().equals(name)) {
                currentLocation.getItems().remove(item);
                return item;
            }
        }
        throw new IllegalArgumentException("Unable to pick up item: " + name);
    }

    public static Location getAvailableLocationByName(String name) {
        for (Location loc : availableLocations) {
            if (loc.getName().equals(name)) {
                return loc;
            }
        }
        throw new IllegalArgumentException("Unable to find location: " + name);
    }

    @Override
    public void printIntro() {
        System.out.println("----------------------------------");
        System.out.println("| Welcome to cli adventure game! |");
        System.out.println("----------------------------------");
        System.out.println();
    }
}
