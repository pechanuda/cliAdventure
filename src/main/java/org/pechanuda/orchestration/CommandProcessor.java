package org.pechanuda.orchestration;

public class CommandProcessor {
    public void processCommand(Command command, GameState gameState) {

        if (command.getCommandType().equals(CommandType.EXIT_COMMAND)) {
            gameState.setGameStatus(GameStatus.EXITED);
            return;
        }

        switch (command.getCommandType()) {
            case CommandType.MOVE_COMMAND -> {
                processMove(command.getSubject(), gameState);
            }
            case CommandType.PICK_COMMAND -> {
                processPick(command.getSubject(), gameState);
            }
            case CommandType.ATTACK_COMMAND -> {
                processAttack(command.getSubject(), gameState);
            }
            case CommandType.TALK_COMMAND -> {
                processTalk(command.getSubject(), gameState);
            }
            case CommandType.USE_COMMAND -> {
                processUse(command.getSubject(), gameState);
            }
            case CommandType.GIVE_COMMAND -> {
                processGive(command.getSubject(), gameState);
            }
            default -> {
                System.out.println("!!!! INVALID STATEMENT !!!!");
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
            gameState.addItemToInventory(gameState.getCurrentLocation().getAndRemoveItemByName(subject));
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
