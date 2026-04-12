package org.pechanuda.orchestration;

import java.util.Scanner;

public class PromptResolution {

    private static Scanner scanner = new Scanner(System.in);

    public static void readPrompt(GameState gameState) {
        String prompt = scanner.nextLine();

        processPrompt(prompt, gameState);
    }

    public static void processPrompt(String prompt, GameState gameState) {

        while (true) {
            if (prompt.split(" ").length > 1) {
                String verb = prompt.split(" ")[0];
                String subject = prompt.substring(verb.length() + 1);

                switch (verb.toLowerCase()) {
                    case "go" -> {
                        processMove(subject ,gameState);
                        return;
                    }
                    case "pick" -> {
                        processPick(subject ,gameState);
                        return;
                    }
                    case "attack" -> {
                        processAttack(subject ,gameState);
                        return;
                    }
                    case "talk" -> {
                        processTalk(subject ,gameState);
                        return;
                    }
                    case "use" -> {
                        processUse(subject ,gameState);
                        return;
                    }
                    case "give" -> {
                        processGive(subject ,gameState);
                        return;
                    }
                    default -> {
                        System.out.println("!!!! INVALID STATEMENT !!!!");
                        return;
                    }
                }
            } else if(
                    prompt.equalsIgnoreCase("EXIT") ||
                    prompt.equalsIgnoreCase("QUIT") ||
                    prompt.equalsIgnoreCase("Q")
            ) {
                gameState.setGameStatus(GameStatus.EXITED);
                return;
            } else {
                System.out.println("!!!! INVALID STATEMENT !!!!");
                return;
            }
        }
    }

    private static void processGive(String subject, GameState gameState) {
        // TODO object parsing
        System.out.println("giving " + subject);
    }

    private static void processAttack(String subject, GameState gameState) {
        System.out.println("attacking " + subject);
    }
    private static void processMove(String subject, GameState gameState) {
        try {
            gameState.setCurrentLocationByName(subject);
            System.out.println("-> going to " + subject);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("!!!! Unavailable location: " + subject + " !!!!");
        }
    }
    private static void processPick(String subject, GameState gameState) {
        try {
            gameState.addItemToInventory(Game.getAvailableItemByName(subject, gameState));
            System.out.println("-> picking up " + subject);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("!!!! Unavailable item: " + subject + " !!!!");
        }
    }
    private static void processTalk(String subject, GameState gameState) {
        System.out.println("talking to " + subject);
    }
    private static void processUse(String subject, GameState gameState) {
        System.out.println("using " + subject);
    }
}
