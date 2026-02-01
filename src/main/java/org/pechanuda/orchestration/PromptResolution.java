package org.pechanuda.orchestration;

import java.util.Scanner;

public class PromptResolution {

    private static Scanner scanner = new Scanner(System.in);

    public static String readPrompt() {
        System.out.println("- You are in: " + Game.getCurrentLocation());
        System.out.println("- Available exits are: " + Game.getCurrentLocation().getExits());
        //        System.out.print("Available exits are: " + currentLocation.getExits().stream().filter(location -> location.isHidden() = false));
        System.out.println("- Available items are: " + Game.getCurrentLocation().getItems());
        System.out.println("- Inventory: " + Game.getInventory().getItems());
        System.out.println("What do you want to do next?");
        System.out.println("----------------------------");
        System.out.println("go <LOCATION> | attack <MONSTER> | pick <ITEM> | use <ITEM>");

        String prompt = scanner.nextLine();

        if (prompt.equalsIgnoreCase("EXIT")) {
            return "EXIT";
        }

        processPrompt(prompt);
        return "continuing...";
    }

    public static void processPrompt(String prompt) {

        while (true) {
            if (prompt.split(" ").length > 1) {
                String verb = prompt.split(" ")[0];
                String subject = prompt.substring(verb.length() + 1);

//                if (subject.contains(" ")) {
//                    processPrompt(prompt);
//                }
                switch (verb.toLowerCase()) {
                    case "go" -> {
                        processMove(subject);
                        return;
                    }
                    case "pick" -> {
                        processPick(subject);
                        return;
                    }
                    case "attack" -> {
                        processAttack(subject);
                        return;
                    }
                    case "talk" -> {
                        processTalk(subject);
                        return;
                    }
                    case "use" -> {
                        processUse(subject);
                        return;
                    }
                    case "give" -> {
                        processGive(subject);
                        return;
                    }
                    default -> {
                        System.out.println("!!!! INVALID STATEMENT !!!!");
                        return;
                    }
                }
            } else {
                System.out.println("!!!! INVALID STATEMENT !!!!");
                return;
            }
        }
    }

    private static void processGive(String subject) {
        // TODO object parsing
        System.out.println("giving " + subject);
    }

    private static void processAttack(String subject) {
        System.out.println("attacking " + subject);
    }
    private static void processMove(String subject) {
        try {
            Game.setCurrentLocationByName(subject);
            System.out.println("-> going to " + subject);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("!!!! Unavailable location: " + subject + " !!!!");
        }
    }
    private static void processPick(String subject) {
        try {
            Game.getInventory().getItems().add(Game.getAvailableItemByName(subject));
            System.out.println("-> picking up " + subject);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("!!!! Unavailable item: " + subject + " !!!!");
        }
    }
    private static void processTalk(String subject) {
        System.out.println("talking to " + subject);
    }
    private static void processUse(String subject) {
        System.out.println("using " + subject);
    }
}
