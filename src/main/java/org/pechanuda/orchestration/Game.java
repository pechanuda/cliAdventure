package org.pechanuda.orchestration;

import org.pechanuda.model.GameWorld;
import org.pechanuda.model.Item;
import org.pechanuda.model.Location;

public class Game implements IGame {

    private GameWorld gameWorld = null;

    public Game() {
    }

    @Override
    public void promptPlayer(GameState gameState) {
        System.out.println("- You are in: " + gameState.getCurrentLocation());
        System.out.println("- Available exits are: " + gameState.getCurrentLocation().getExits());
        System.out.println("- Available items are: " + gameState.getCurrentLocation().getItems());
        System.out.println("- Present monsters are: " + gameState.getCurrentLocation().getMonsters());
        System.out.println("- Inventory: " + gameState.getInventory().getItems());
        System.out.println("What do you want to do next?");
        System.out.println("----------------------------");
        System.out.println("go <LOCATION> | attack <MONSTER> | pick <ITEM> | use <ITEM>");

        PromptResolution.readPrompt(gameState);
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
        gameState.initInventory();
    }

    @Override
    public void printOutro(GameState gameState) {
        if (gameState.getGameStatus().equals(GameStatus.VICTORY)) {
            System.out.println("Congratz!");
        } if (gameState.getGameStatus().equals(GameStatus.EXITED)) {
            System.out.println("Sorry to hear that you're leaving, see you next time!");
        } else {
            System.out.println("gg");
        }
    }

    public static Item getAvailableItemByName(String name, GameState gameState) {
        for (Item item : gameState.getCurrentLocation().getItems()) {
            if (item.getName().equals(name)) {
                gameState.getCurrentLocation().getItems().remove(item);
                return item;
            }
        }
        throw new IllegalArgumentException("Unable to pick up item: " + name);
    }

    public static Location getAvailableLocationByName(String name, GameState gameState) {
        for (Location loc : gameState.getAvailableLocations()) {
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
