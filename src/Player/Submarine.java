package Player;

import Entity.*;

public class Submarine extends Entity {
    Inventory inventory;
    double fuel;
    double oxygen;

    int fuelCapacityLevel = 0;
    int oxygenCapacityLevel = 0;
    int pickupRadiusLevel = 0;
    int inventoryCapacityLevel = 0;

    Submarine() {
        super(new Point(0, 0));
        fuel = UpgradeState.fuelUpgrades[fuelCapacityLevel];
        oxygen = UpgradeState.oxygenUpgrades[oxygenCapacityLevel];
        inventory = new Inventory(UpgradeState.inventoryUpgrades[inventoryCapacityLevel]);
    }

    public int upgradeInventorylevel() {
        return ++inventoryCapacityLevel;
    }

    public int upgradeFuelLevel() {
        return ++fuelCapacityLevel;
    }

    public int upgradeOxygenLevel() {
        return ++oxygenCapacityLevel;
    }

    public int upgradePickupRadius() {
        return ++pickupRadiusLevel;
    }

    int getInventoryCapacity() {
        return UpgradeState.inventoryUpgrades[inventoryCapacityLevel];
    }

    double getFuelCapacity() {
        return UpgradeState.fuelUpgrades[fuelCapacityLevel];
    }

    double getOxygenCapacity() {
        return UpgradeState.oxygenUpgrades[oxygenCapacityLevel];
    }

    double pickUpRadius() {
        return UpgradeState.pickupRadiusUpgrades[pickupRadiusLevel];
    }

    // picks up all the items in range, and adds them to inventory, unless inventory is full
    void pickupItems() {

    }

    // moves the submarines and spends the fuel necessary
    void moveTo(Point p) {

    }

}

class UpgradeState {
    static int[] inventoryUpgrades = {6, 8, 10, 12, 14, 16};
    static double[] fuelUpgrades = {1000, 1200, 1400, 1600, 1800, 2000};
    static double[] oxygenUpgrades = {2000, 2500, 3000, 3500, 4000, 4500};
    static double[] pickupRadiusUpgrades = {0.5, 0.75, 1.0, 1.25, 1.5, 1.75};
}
