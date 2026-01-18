package org.pechanuda.orchestration;

import java.util.ArrayList;
import java.util.List;

import org.pechanuda.model.Inventory;
import org.pechanuda.model.Location;

public class Game implements IGame {

    private GameStatus gameStatus = GameStatus.IN_PROGRESS;
    private Inventory inventory = new Inventory();

    private static List<Location> availableLocations = new ArrayList<>();
    private static Location currentLocation;

    public List<Location> getLocations() {
        return availableLocations;
    }

    public static List<Location> getAvailableLocations() {
        return availableLocations;
    }

    public static void setAvailableLocations(List<Location> locations) {
        Game.availableLocations = locations;
    }

    public static Location getCurrentLocation() {
        return currentLocation;
    }

    public static void setCurrentLocation(Location currentLocation) {
        Game.currentLocation = currentLocation;
    }

    public static void setCurrentLocationByName(String chosenLocation) {
        setCurrentLocation(getAvailableLocationByName(chosenLocation));
        setAvailableLocations(currentLocation.getExits());
    }

    public Game() {
    }


    @Override
    public void promptPlayer() {
//        System.out.println("You are in: " + currentLocation);
//        System.out.println("Available exits are: " + currentLocation.getExits());
////        System.out.print("Available exits are: " + currentLocation.getExits().stream().filter(location -> location.isHidden() = false));
//        System.out.println("Available items are: " + currentLocation.getItems());
//        System.out.println("What do you want to do next?");
//        System.out.println("go <LOCATION> | attack <MONSTER> | pick <ITEM> | use <ITEM>");

        String promptResult = PromptResolution.readPrompt();

        if (promptResult.equalsIgnoreCase("exit")) {
            this.gameStatus = GameStatus.EXITED;
        }
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }


    @Override
    public void initGame() {

        initLocations();
        initItems();
        initMonsters();
        initNPCs();
        initInventory();
    }

    private void initLocations() {
        Location oldCrossroad = new Location("Old crossroad");
        Location tavernByTheBlindMonk = new Location("Tavern by the Blind Monk");
        Location eelsLake = new Location("Eel's Lake");
        Location arrakeenCityMainGate = new Location("Arakeen City Main Gate");
        Location arrakeenCityThroneRoom = new Location("Arakeen Throne room");
        Location arrakeenCityMarket = new Location("Arakeen City market");
        Location arrakeenCityBank = new Location("Arakeen City bank");
        Location arrakeenCityBlacksmith = new Location("Arakeen City blacksmith");
        Location arrakeenCitySewers = new Location("Arakeen City sewers");


        oldCrossroad.setExits(List.of(tavernByTheBlindMonk, eelsLake, arrakeenCityMainGate));
        arrakeenCityMainGate.setExits(List.of(oldCrossroad, arrakeenCityBank, arrakeenCityMarket, arrakeenCityBlacksmith, arrakeenCityThroneRoom, arrakeenCitySewers));
        tavernByTheBlindMonk.setExits(List.of(oldCrossroad));
        eelsLake.setExits(List.of(oldCrossroad));
        arrakeenCityThroneRoom.setExits(List.of(arrakeenCityMainGate));
        arrakeenCityMarket.setExits(List.of(arrakeenCityMainGate));
        arrakeenCityBank.setExits(List.of(arrakeenCityMainGate));
        arrakeenCityBlacksmith.setExits(List.of(arrakeenCityMainGate));
        arrakeenCitySewers.setExits(List.of(arrakeenCityMainGate));

        currentLocation = oldCrossroad;
        availableLocations = oldCrossroad.getExits();
    }

    private void initNPCs() {

    }

    private void initMonsters() {

    }

    private void initInventory() {

    }

    private void initItems() {

    }

    @Override
    public void printOutro() {
        if (getGameStatus().equals(GameStatus.VICTORY)) {
            System.out.println("Congratz!");
        } if (getGameStatus().equals(GameStatus.EXITED)) {
            System.out.println("Sorry to hear that you're leaving, see you next time!");
        } else {
            System.out.println("gg");
        }
    }

    public static Location getAvailableLocationByName(String name) {
        for (Location loc : availableLocations) {
            if (loc.getName().equals(name)) {
                return loc;
            }
        }
        throw new IllegalArgumentException("Unable to find location: " + name);
    }

    @Override
    public void printIntro() {
        System.out.println("----------------------------------");
        System.out.println("| Welcome to cli adventure game! |");
        System.out.println("----------------------------------");
        System.out.println();
    }
}
