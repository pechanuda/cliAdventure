package org.pechanuda.orchestration;

public interface IGame {

    void initGame();
    void printIntro();
    void printOutro();
    void promptPlayer();

    GameStatus getGameStatus();
}
