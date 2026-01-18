package org.pechanuda;

import org.pechanuda.orchestration.Game;
import org.pechanuda.orchestration.GameStatus;

public class Main {

    private static boolean isGameOver = false;
    private static boolean isWinner = false;
    private static boolean isExited = false;

    public static void main(String[] args) {

        Game game = new Game();

        game.printIntro();
        game.initGame();

        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            game.promptPlayer();
        }

        game.printOutro();
    }
}
