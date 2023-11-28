package Player;

import Entity.*;
import Items.Item;
import World.*;
import Globals.Globals;

public class Submarine extends Entity {
    public Inventory inventory;
    double fuel;
    double oxygen;
    int hull;

    int fuelCapacityLevel = 0;
    int oxygenCapacityLevel = 0;
    int pickupRadiusLevel = 0;
    int inventoryCapacityLevel = 0;
    int hullStrengthLevel = 0;

    // position is only relative to current screen
    public int x = (Globals.tilesPerScreen / 2);
    public int y = (Globals.tilesPerScreen / 2);
    public int z = 0;

    Submarine() {
        super(new Point(0, 0));
        fuel = Globals.fuelUpgrades[fuelCapacityLevel];
        oxygen = Globals.oxygenUpgrades[oxygenCapacityLevel];
        hull = Globals.hullStrengthUpgrades[hullStrengthLevel];
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

    public int upgradeHullStrengthLevel() {
        return ++hullStrengthLevel;
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

    public int getOxygenCapacityLevel() {
        return oxygenCapacityLevel;
    }
    public int getHullStrengthLevel() {
        return hullStrengthLevel;
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
    void pickupItems(Screen screen) {
        for (int i = 0; i < screen.entities.size(); i++) {
            Point subPos = new Point((double)x + 0.5, (double)y + 0.5);
            Entity entity = screen.entities.get(i);
            if (entity instanceof Item) {
                if (Point.distance(entity.getPosition(), subPos) <= this.getPickUpRadius()) {
                    if (inventory.addItem((Item) entity) == 1) {
                        screen.entities.remove(i);
                        i--;
                    }
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

        Screen currentScreen = world.currentScreen;

        if (dir.equals("east")) {
            if (x >= Globals.tilesPerScreen - 1) {
                // we move screens, provided we are allowed to move into the space
                currentScreen = world.getAdjacentScreen(dir);
                if (currentScreen.map[y][0][z].available) {
                    x = 0;
                    world.moveToAdjacentScreen(dir);
                    return true;
                }
            }
            else if (currentScreen.map[y][x+1][z].available) {
                x++;
                return true;
            }
        }
        else if (dir.equals("west")) {
            if (x <= 0) {
                // we move screens, provided we are allowed to move into the space
                currentScreen = world.getAdjacentScreen(dir);
                if (currentScreen.map[y][Globals.tilesPerScreen - 1][z].available) {
                    x = Globals.tilesPerScreen - 1;
                    world.moveToAdjacentScreen(dir);
                    return true;
                }
            }
            else if (currentScreen.map[y][x-1][z].available) {
                x--;
                return true;
            }
        }
        if (dir.equals("north")) {
            if (y <= 0) {
                // we move screens, provided we are allowed to move into the space
                currentScreen = world.getAdjacentScreen(dir);
                if (currentScreen.map[Globals.tilesPerScreen - 1][x][z].available) {
                    y = Globals.tilesPerScreen - 1;
                    world.moveToAdjacentScreen(dir);
                    return true;
                }
            }
            else if (currentScreen.map[y-1][x][z].available) {
                y--;
                return true;
            }
        }
        else if (dir.equals("south")) {
            if (y >= Globals.tilesPerScreen - 1) {
                // we move screens, provided we are allowed to move into the space
                currentScreen = world.getAdjacentScreen(dir);
                if (currentScreen.map[0][x][z].available) {
                    y = 0;
                    world.moveToAdjacentScreen(dir);
                    return true;
                }
            }
            if (currentScreen.map[y+1][x][z].available) {
                y++;
                return true;
            }
        }
        if (dir.equals("down")) {
            if (z >= Globals.layers - 1)
                return false;
            if (currentScreen.map[y][x][z+1].available && hullStrengthLevel > 0) {
                z++;
                return true;
            }
        }
        else if (dir.equals("up")) {
            if (z <= 0)
                return false;
            if (currentScreen.map[y][x][z-1].available) {
                z--;
                return true;
            }
        }
        return false;
    }

}
