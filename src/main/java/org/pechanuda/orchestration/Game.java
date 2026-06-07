package org.pechanuda.orchestration;

import org.pechanuda.model.GameWorld;

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
        } else if (gameState.getGameStatus().equals(GameStatus.EXITED)) {
            System.out.println("Sorry to hear that you're leaving, see you next time!");
        } else {
            System.out.println("gg");
        }
    }

    @Override
    public void printIntro() {
        System.out.println("----------------------------------");
        System.out.println("| Welcome to cli adventure game! |");
        System.out.println("----------------------------------");
        System.out.println();
    }
}
