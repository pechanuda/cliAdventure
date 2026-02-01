package org.pechanuda;

import org.pechanuda.model.GameWorld;
import org.pechanuda.orchestration.Game;
import org.pechanuda.orchestration.GameStatus;
import org.pechanuda.orchestration.WorldLoader;

public class Main {

    private static boolean isGameOver = false;
    private static boolean isWinner = false;
    private static boolean isExited = false;
    private static WorldLoader worldLoader = new WorldLoader();

    public static void main(String[] args) {

        Game game = new Game();

        game.setGameWorld(worldLoader.load("arakeen.json"));

        game.printIntro();
        game.initGame();

        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            game.promptPlayer();
        }

        game.printOutro();
    }
}
