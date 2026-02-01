package org.pechanuda.orchestration;

import org.pechanuda.model.GameWorld;

public interface IGame {

    void initGame();
    void printIntro();
    void printOutro();
    void promptPlayer();

    GameStatus getGameStatus();

    void setGameWorld(GameWorld gameWorld);
    GameWorld getGameWorld();
}
