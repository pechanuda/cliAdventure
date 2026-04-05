package org.pechanuda.orchestration;

import org.pechanuda.model.GameWorld;

public interface IGame {

    void initGame(GameState gameState);
    void printIntro();
    void printOutro();
    void promptPlayer(GameState gameState);

    GameStatus getGameStatus();

    void setGameWorld(GameWorld gameWorld);
    GameWorld getGameWorld();
}
