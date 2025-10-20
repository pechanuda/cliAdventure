package org.pechanuda;

import java.util.List;
import java.util.Scanner;

import org.pechanuda.model.Inventory;
import org.pechanuda.model.Location;
import org.pechanuda.orchestration.PromptResolution;

public class Main {

    private static boolean isGameOver = false;
    private static boolean isWinner = false;
    private static boolean isExited = false;
    private static Inventory inventory = new Inventory();
    private static Scanner scanner;

    private static Location currentLoc;

    public static void main(String[] args) {

        printIntro();
        initGame();

        while (!isGameOver) {
            promptPlayer();
        }

        printOutro();
    }

    private static void promptPlayer() {
        System.out.println("You are in: " + currentLoc);
        System.out.println("Available exits are: " + currentLoc.getExits());
        System.out.println("Available items are: " + currentLoc.getItems());
        System.out.println("What do you want to do next?");
        System.out.println("go <LOCATION> | attack <MONSTER> | pick <ITEM> | use <ITEM>");

        String prompt = scanner.nextLine();

        String gameStatus = PromptResolution.processPrompt(prompt);

        if (gameStatus.equalsIgnoreCase("exit")) {
            isGameOver = true;
            isExited = true;
        }
    }

    private static void initGame() {
        scanner = new Scanner(System.in);

        initLocations();
        initItems();
        initMonsters();
        initNPCs();
        initInventory();
    }

    private static void initLocations() {
        Location oldCrossroad = new Location("Old crossroad");
        Location tavernByTheBlindMonk = new Location("Tavern by the Blind Monk");
        oldCrossroad.setExits(List.of(tavernByTheBlindMonk));

        currentLoc = oldCrossroad;
    }

    private static void initNPCs() {

    }

    private static void initMonsters() {

    }

    private static void initInventory() {

    }

    private static void initItems() {

    }

    private static void printOutro() {
        if (isWinner) {
            System.out.println("Congratz!");
        } if (isExited) {
            System.out.println("Sorry to hear that you're leaving, see you next time!");
        } else {
            System.out.println("gg");
        }
    }

    private static void printIntro() {
        System.out.println("Welcome to cli adventure game!");
    }
}
