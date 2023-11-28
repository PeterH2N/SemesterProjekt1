package Globals;

public class Globals {
    final public static String JSONPath = "res/JSON/";
    final public static String mapGenPath = "res/MapGen/";
    final public static String spritePath = "res/Sprites/";
    final public static int tilesPerScreen = 11;
    final public static int layers = 5;

    final public static int defaultItemStackSize = 16;
    final public static double fuelConsumptionPerMove = 0.;
    final public static double fuelConsumptionPerPickUp = 0.1;
    final public static int  oxygenConsumptionPerMove = 50;

    final public static String[] inventoryUpgradeItemName = {"Glass", "PET"};
    final public static String[] fuelUpgradeItemName = {"AlU"};
    final public static String[] oxygenUpgradeItemName = {"PVC", "HDPE"};

    final public static int[][] inventoryUpgradeItemAmount = {  {3, 6, 9, 12, 15},
                                                                {2, 4, 6, 8, 10}};
    final public static int[][] fuelUpgradeItemAmount = {{2, 4, 6, 8, 10}};
    final public static  int[][] oxygenUpgradeItemAmount = {    {3, 6, 9, 12, 15},
                                                                {2, 4, 6, 8, 10}};

    // Submarine upgrades //
    final public static int[] inventoryUpgrades = {6, 8, 10, 12, 14, 16};
    final public static double[] fuelUpgrades = {100, 110, 125, 140, 170, 200};
    final public static double[] oxygenUpgrades = {2000, 2500, 3000, 3500, 4000, 5000};
    final public static double[] pickupRadiusUpgrades = {2.0, 2.25, 2.5, 3.0, 3.5};
}
