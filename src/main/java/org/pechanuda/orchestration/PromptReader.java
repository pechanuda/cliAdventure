package org.pechanuda.orchestration;

import java.util.Scanner;

public class PromptReader {

    private static Scanner scanner = new Scanner(System.in);
    private static CommandParser commandParser = new CommandParser();
    private static CommandProcessor commandProcessor = new CommandProcessor();

    public static void readPrompt(GameState gameState) {
        if (scanner.hasNextLine()) {
            String prompt = scanner.nextLine();

            Command command = commandParser.parsePrompt(prompt);

            commandProcessor.processCommand(command, gameState);

        } else {
            System.out.println("No input, exiting");
            gameState.setGameStatus(GameStatus.EXITED);
        }
    }
}
