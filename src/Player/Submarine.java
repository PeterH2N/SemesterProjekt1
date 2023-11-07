package Player;

import Entity.*;
import Items.Item;
import World.*;
import Globals.Globals;

public class Submarine extends Entity {
    public Inventory inventory;
    double fuel;
    double oxygen;

    int fuelCapacityLevel = 0;
    int oxygenCapacityLevel = 0;
    int pickupRadiusLevel = 0;
    int inventoryCapacityLevel = 0;

    int x = (Globals.worldSize / 2);
    int y = (Globals.worldSize  / 2);
    int z = 0;

    Submarine() {
        super(new Point(0, 0));
        fuel = UpgradeState.fuelUpgrades[fuelCapacityLevel];
        oxygen = UpgradeState.oxygenUpgrades[oxygenCapacityLevel];
        inventory = new Inventory(UpgradeState.inventoryUpgrades[inventoryCapacityLevel]);
    }

    public int upgradeInventorylevel() {
        inventoryCapacityLevel++;
        inventory.increaseCapacity(getInventoryCapacity());
        return inventoryCapacityLevel;
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

    public double getFuelLevel() {
        return fuel;
    }
    double getOxygenLevel() {
        return oxygen;
    }

    // picks up all the items in range, and adds them to inventory, unless inventory is full
    void pickupItems(Space space) {
        for (int i = 0; i < space.entities.size(); i++) {
            if (space.entities.get(i) instanceof Item) {
                inventory.addItem((Item)space.entities.get(i));

                space.entities.remove(i);
                i--;
            }

        }
    }

    // moves the submarines and spends the fuel necessary
    boolean move(String dir, World world) {
        if (fuel < Globals.fuelConsumptionPerMove) {
            System.out.println("Not enough fuel!");
            return false;
        }

        if (dir.equals("east")) {
            if (x >= Globals.worldSize - 1)
                return false;
            if (world.map[y][x+1][z].available) {
                x++;
                return true;
            }
        }
        else if (dir.equals("west")) {
            if (x <= 0)
                return false;
            if (world.map[y][x-1][z].available) {
                x--;
                return true;
            }
        }
        if (dir.equals("north")) {
            if (y <= 0)
                return false;
            if (world.map[y-1][x][z].available) {
                y--;
                return true;
            }
        }
        else if (dir.equals("south")) {
            if (y >= Globals.worldSize - 1)
                return false;
            if (world.map[y+1][x][z].available) {
                y++;
                return true;
            }
        }
        if (dir.equals("down")) {
            if (z >= Globals.layers - 1)
                return false;
            if (world.map[y][x][z+1].available) {
                z++;
                return true;
            }
        }
        else if (dir.equals("up")) {
            if (z <= 0)
                return false;
            if (world.map[y][x][z-1].available) {
                z--;
                return true;
            }
        }

        return false;
    }

}

class UpgradeState {
    static int[] inventoryUpgrades = {6, 8, 10, 12, 14, 16};
    static double[] fuelUpgrades = {100, 120, 140, 160, 180, 200};
    static double[] oxygenUpgrades = {2000, 2500, 3000, 3500, 4000, 4500};
    static double[] pickupRadiusUpgrades = {0.5, 0.75, 1.0, 1.25, 1.5, 1.75};
}
