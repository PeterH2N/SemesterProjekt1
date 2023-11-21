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

    int x = (Globals.ScreenSize / 2);
    int y = (Globals.ScreenSize / 2);
    int z = 0;

    Submarine() {
        super(new Point(0, 0));
        fuel = Globals.fuelUpgrades[fuelCapacityLevel];
        oxygen = Globals.oxygenUpgrades[oxygenCapacityLevel];
        inventory = new Inventory(Globals.inventoryUpgrades[inventoryCapacityLevel]);
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

    public int getInventoryCapacity() {
        return Globals.inventoryUpgrades[inventoryCapacityLevel];
    }
    
    public int getInventoryCapacityLevel() {
        return inventoryCapacityLevel;
    }

    public double getFuelCapacity() {
        return Globals.fuelUpgrades[fuelCapacityLevel];
    }
    
    public int getFuelCapacityLevel() {
        return fuelCapacityLevel;
    }

    public double getOxygenCapacity() {
        return Globals.oxygenUpgrades[oxygenCapacityLevel];
    }

    public double getPickUpRadius() {
        return Globals.pickupRadiusUpgrades[pickupRadiusLevel];
    }

    public double getFuel() {
        return fuel;
    }
    public double getOxygen() {
        return oxygen;
    }

    // picks up all the items in range, and adds them to inventory, unless inventory is full
    void pickupItems(Space space) {
        for (int i = 0; i < space.entities.size(); i++) {
            if (space.entities.get(i) instanceof Item) {
                if (inventory.addItem((Item)space.entities.get(i)) == 1) {
                    space.entities.remove(i);
                    i--;
                }
            }

        }
    }

    // moves the submarines and spends the fuel necessary
    boolean move(String dir, World world) {
        if (fuel < Globals.fuelConsumptionPerMove) {
            System.out.println("Not enough fuel!");
            return false;
        }

        /*if (dir.equals("east")) {
            if (x >= Globals.ScreenSize - 1)
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
            if (y >= Globals.ScreenSize - 1)
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
        }*/
        return false;
    }

}
