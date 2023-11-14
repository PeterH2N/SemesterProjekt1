package Globals;

public class Globals {
    final public static String JSONPath = "res/JSON/";
    final public static String MapGenPath = "res/MapGen/";
    final public static int worldSize = 21;
    final public static int layers = 5;

    final public static int defaultItemStackSize = 16;
    final public static double fuelConsumptionPerMove = 5.0;
    final public static double fuelConsumptionPerPickUp = 1.0;

    final public static String[] inventoryUpgradeItemName = {"Glass", "PET"};
    final public static String[] fuelUpgradeItemName = {"AlU"};
    final public static int[][] inventoryUpgradeItemAmount = {  {3, 6, 9, 12, 15},
                                                                {2, 4, 6, 8, 10}};
    final public static int[][] fuelUpgradeItemAmount = {{2, 4, 6, 8, 10}};

    // Submarine upgrades //
    final public static int[] inventoryUpgrades = {6, 8, 10, 12, 14, 16};
    final public static double[] fuelUpgrades = {100, 120, 140, 160, 180, 200};
    final public static double[] oxygenUpgrades = {2000, 2500, 3000, 3500, 4000, 4500};
    final public static double[] pickupRadiusUpgrades = {0.5, 0.75, 1.0, 1.25, 1.5, 1.75};
}
