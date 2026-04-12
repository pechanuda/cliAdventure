package org.pechanuda.orchestration;

import org.pechanuda.model.GameWorld;

public interface IGame {

    void initGame(GameState gameState);
    void printIntro();
    void printOutro(GameState gameState);
    void promptPlayer(GameState gameState);
    void setGameWorld(GameWorld gameWorld);
    GameWorld getGameWorld();
}
