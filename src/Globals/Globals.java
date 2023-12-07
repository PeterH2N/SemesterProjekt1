package Globals;

import java.util.HashMap;
import java.util.Map;

public class Globals {

    final public static String JSONPath = "res/JSON/";
    final public static String mapGenPath = "res/MapGen/";
    final public static String spritePath = "res/Sprites/";

    public static String globalMessage = "";
    final public static int tilesPerScreen = 11;
    final public static int layers = 5;

    final public static double workShopSpawnRate = 0.2;

    final public static double shopSpawnRate = 0.5;

    final public static int defaultItemStackSize = 16;
    final public static double fuelConsumptionPerMove = 0.25;
    final public static double fuelConsumptionPerPickUp = 0.5;
    final public static double oxygenConsumptionPerMove = 50;

    final public static double fuelPricePerLitre = 200;

    final public static String inventory = "inventory";
    final public static String fuel = "fuel";
    final public static String oxygen = "oxygen";

    final public static String hull = "hull";
    final public static String pickup = "pickup";

    public static Map<String, String[]> upgradeItemName= new HashMap<>();
    public static Map<String, Integer[][]> upgradeItemAmount= new HashMap<>();

    public static Map<String, Double[]> upgrades= new HashMap<>();

    static {
        // inventory upgrades
        upgradeItemName.put("inventory", new String[]{"Glass", "PET"});
        upgradeItemAmount.put("inventory", new Integer[][]{
                {3, 6, 9, 12, 15},
                {2, 4, 6, 8, 10}});

        upgrades.put("inventory", new Double[]{6.0, 8.0, 10.0, 12.0, 14.0, 16.0});

        // fuel upgrades
        upgradeItemName.put("fuel", new String[]{"ALU"});
        upgradeItemAmount.put("fuel", new Integer[][]{
                {2, 4, 6, 8, 10}});

        upgrades.put("fuel", new Double[]{100.0, 120.0, 140.0, 160.0, 180.0, 200.0});

        // oxygen upgrades
        upgradeItemName.put("oxygen", new String[]{"LDPE", "PVC"});
        upgradeItemAmount.put("oxygen", new Integer[][]{
                {3, 6, 9, 12, 15},
                {2, 4, 6, 8, 10}});

        upgrades.put("oxygen", new Double[]{2000.0, 2500.0, 3000.0, 3500.0, 4000.0, 4500.0});

        // hull upgrades
        upgradeItemName.put("hull", new String[]{"ALU", "Glass", "HDPE"});
        upgradeItemAmount.put("hull", new Integer[][]{
                {3, 6, 9, 12},
                {2, 4, 6, 8},
                {1, 2, 3, 4}});

        upgrades.put("hull", new Double[]{1.0, 2.0, 3.0, 4.0});

        // pickup upgrades
        upgradeItemName.put("pickup", new String[]{"LDPE", "PP"});
        upgradeItemAmount.put("pickup", new Integer[][]{
                {3, 6, 9, 12, 15},
                {2, 4, 6, 8, 10}});

        upgrades.put("pickup", new Double[]{2.0, 2.25, 2.5, 3.0, 3.5});


    }

    /*final public static String[] inventoryUpgradeItemName = {"Glass", "PET"};
    final public static String[] fuelUpgradeItemName = {"ALU"};
    final public static String[] oxygenUpgradeItemName = {"LDPE", "PVC"};
    final public static String[] hullStrengthUpgradeItemName = {"ALU", "Glass", "HDPE"};
    final public static String[] pickupRadiusUpgradeItemName = {"LDPE", "PP"};
    final public static int[][] inventoryUpgradeItemAmount = {{3, 6, 9, 12, 15},
            {2, 4, 6, 8, 10}};
    final public static int[][] fuelUpgradeItemAmount = {{2, 4, 6, 8, 10}};
    final public static int[][] oxygenUpgradeItemAmount = {{3, 6, 9, 12, 15},
            {2, 4, 6, 8, 10}};
    final public static int[][] hullStrengthUpgradeItemAmount = {{3, 6, 9, 12},
            {2, 4, 6, 8},
            {1, 2, 3, 4}};
    final public static int[][] pickupRadiusUpgradeItemAmount = {{3, 6, 9, 12, 15},
            {2, 4, 6, 8, 10}};*/

    // Submarine upgrades //
    /*final public static int[] inventoryUpgrades = {6, 8, 10, 12, 14, 16};
    final public static double[] fuelUpgrades = {100, 120, 140, 160, 180, 200};
    final public static double[] oxygenUpgrades = {2000, 2500, 3000, 3500, 4000, 4500};
    final public static double[] pickupRadiusUpgrades = {2.0, 2.25, 2.5, 3.0, 3.5};
    final public static int[] hullStrengthUpgrades = {1, 2, 3, 4};*/

}