package Buildings;

import Entity.Point;
import Globals.Globals;
import Items.Item;
import Items.MaterialItem;
import Items.WasteItem;
import Items.WasteType;
import Player.*;
import java.util.function.DoubleFunction;

public class WorkShop extends Building {
    public WorkShop(Point pos){
        super("This is a workshop", "Workshop", pos);
    }

    public boolean upgradeInventoryCapacity(Player player){
        // check if we are fully upgraded
        if (player.sub.getInventoryCapacityLevel() == Globals.inventoryUpgrades.length - 1) {
            Globals.globalMessage = "Inventory is already fully upgraded!";
            return false;
        }
        // make sure we have all the items needed
        for (int i = 0; i < Globals.inventoryUpgradeItemAmount.length; i++) {
            if (!player.sub.inventory.containsItems(Globals.inventoryUpgradeItemName[i], Globals.inventoryUpgradeItemAmount[i][player.sub.getInventoryCapacityLevel()])) {
                Globals.globalMessage = ("You dont have the required items! You need: \n");
                for (int j = 0; j < Globals.inventoryUpgradeItemName.length; j++){
                    Globals.globalMessage +=
                            Globals.inventoryUpgradeItemName[j] + " x " +
                            Globals.inventoryUpgradeItemAmount[j][player.sub.getInventoryCapacityLevel()] + "\n";
                }
                return false;
            }
        }
        // now we remove the items
        for (int i = 0; i < Globals.inventoryUpgradeItemName.length; i++) {
            String itemName = Globals.inventoryUpgradeItemName[i];
            int amount = Globals.inventoryUpgradeItemAmount[i][player.sub.getInventoryCapacityLevel()];
            player.sub.inventory.removeItem(itemName, amount);
        }
        // lastly, we upgrade
        player.sub.upgradeInventorylevel();
        Globals.globalMessage = "Upgraded Inventory Capacity!";
        return true;
    }

    public boolean upgradeFuelCapacity(Player player) {
        // check if already fully upgraded
        if (player.sub.getFuelCapacityLevel() == Globals.fuelUpgrades.length - 1) {
            Globals.globalMessage = ("Fuel capacity is already fully upgraded!");
            return false;
        }
        // make sure we have all the items needed
        for (int i = 0; i < Globals.fuelUpgradeItemName.length; i++) {
            if (!player.sub.inventory.containsItems(Globals.fuelUpgradeItemName[i], Globals.fuelUpgradeItemAmount[i][player.sub.getFuelCapacityLevel()])) {
                Globals.globalMessage = ("You dont have the required items! You need: \n");
                for (int j = 0; j < Globals.fuelUpgradeItemName.length; j++){
                    Globals.globalMessage +=
                            Globals.fuelUpgradeItemName[j] + " x " +
                                    Globals.fuelUpgradeItemAmount[j][player.sub.getFuelCapacityLevel()] + "\n";
                }
                return false;
            }
        }
        // now we remove the items
        for (int i = 0; i < Globals.fuelUpgradeItemName.length; i++) {
            String itemName = Globals.fuelUpgradeItemName[i];
            int amount = Globals.fuelUpgradeItemAmount[i][player.sub.getFuelCapacityLevel()];
            player.sub.inventory.removeItem(itemName, amount);
        }
        // lastly, we upgrade
        player.sub.upgradeFuelLevel();
        Globals.globalMessage = "Upgraded Fuel Capacity!";
        return true;
    }

    public boolean upgradeOxygenCapacity(Player player) {
        // check if already fully upgraded
        if (player.sub.getOxygenCapacityLevel() == Globals.oxygenUpgrades.length - 1) {
            Globals.globalMessage = ("Oxygen capacity is already fully upgraded!");
            return false;
        }
        // make sure we have all the items needed
        for (int i = 0; i < Globals.oxygenUpgradeItemName.length; i++) {
            if (!player.sub.inventory.containsItems(Globals.oxygenUpgradeItemName[i], Globals.oxygenUpgradeItemAmount[i][player.sub.getOxygenCapacityLevel()])) {
                Globals.globalMessage = ("You dont have the required items! You need: \n");
                for (int j = 0; j < Globals.oxygenUpgradeItemName.length; j++){
                    Globals.globalMessage +=
                            Globals.oxygenUpgradeItemName[j] + " x " +
                                    Globals.oxygenUpgradeItemAmount[j][player.sub.getOxygenCapacityLevel()] + "\n";
                }
                return false;
            }
        }
        // now we remove the items
        for (int i = 0; i < Globals.oxygenUpgradeItemName.length; i++) {
            String itemName = Globals.oxygenUpgradeItemName[i];
            int amount = Globals.oxygenUpgradeItemAmount[i][player.sub.getOxygenCapacityLevel()];
            player.sub.inventory.removeItem(itemName, amount);
        }
        // lastly, we upgrade
        player.sub.upgradeOxygenLevel();
        Globals.globalMessage = "Upgraded Oxygen Capacity!";
        return true;
    }

    public boolean upgradeHullStrength(Player player) {
        // check if already fully upgraded
        if (player.sub.getHullStrengthLevel() == Globals.hullStrengthUpgrades.length - 1) {
            Globals.globalMessage = ("Hull strength is already fully upgraded!");
            return false;
        }
        // make sure we have all the items needed
        for (int i = 0; i < Globals.hullStrengthUpgradeItemName.length; i++) {
            if (!player.sub.inventory.containsItems(Globals.hullStrengthUpgradeItemName[i], Globals.hullStrengthUpgradeItemAmount[i][player.sub.getHullStrengthLevel()])) {
                Globals.globalMessage = ("You dont have the required items! You need: \n");
                for (int j = 0; j < Globals.hullStrengthUpgradeItemName.length; j++){
                    Globals.globalMessage +=
                            Globals.hullStrengthUpgradeItemName[j] + " x " +
                                    Globals.hullStrengthUpgradeItemAmount[j][player.sub.getHullStrengthLevel()] + "\n";
                }
                return false;
            }
        }
        // now we remove the items
        for (int i = 0; i < Globals.hullStrengthUpgradeItemName.length; i++) {
            String itemName = Globals.hullStrengthUpgradeItemName[i];
            int amount = Globals.hullStrengthUpgradeItemAmount[i][player.sub.getHullStrengthLevel()];
            player.sub.inventory.removeItem(itemName, amount);
        }
        // lastly, we upgrade
        player.sub.upgradeHullStrengthLevel();
        Globals.globalMessage = "Upgraded Hull Strength!";
        return true;
    }

    public boolean upgradePickupRadius(Player player) {
        // check if already fully upgraded
        if (player.sub.getPickupRadiusLevel() == Globals.pickupRadiusUpgrades.length - 1) {
            Globals.globalMessage = ("Pickup radius is already fully upgraded!");
            return false;
        }
        // make sure we have all the items needed
        for (int i = 0; i < Globals.pickupRadiusUpgradeItemName.length; i++) {
            if (!player.sub.inventory.containsItems(Globals.pickupRadiusUpgradeItemName[i], Globals.pickupRadiusUpgradeItemAmount[i][player.sub.getPickupRadiusLevel()])) {
                Globals.globalMessage = ("You dont have the required items! You need: \n");
                for (int j = 0; j < Globals.pickupRadiusUpgradeItemName.length; j++){
                    Globals.globalMessage +=
                            Globals.pickupRadiusUpgradeItemName[j] + " x " +
                                    Globals.pickupRadiusUpgradeItemAmount[j][player.sub.getPickupRadiusLevel()] + "\n";
                }
                return false;
            }
        }
        // now we remove the items
        for (int i = 0; i < Globals.pickupRadiusUpgradeItemName.length; i++) {
            String itemName = Globals.pickupRadiusUpgradeItemName[i];
            int amount = Globals.pickupRadiusUpgradeItemAmount[i][player.sub.getPickupRadiusLevel()];
            player.sub.inventory.removeItem(itemName, amount);
        }
        // lastly, we upgrade
        player.sub.upgradePickupRadius();
        Globals.globalMessage = "Upgraded Pickup Radius!";
        return true;
    }
    public boolean smelt(Player player, int itemSlot) {
        InventorySlot slot = player.sub.inventory.slots[itemSlot];

        if (slot==null){
            return false;
        }
        if (!(slot.item instanceof Items.WasteItem)) {
            Globals.globalMessage = "Only waste items can be smelted";
            return false;
        }
        WasteItem waste = (Items.WasteItem) slot.item;
        String materialKey = waste.getMaterialKey();

        int amount = slot.amount;

        player.sub.inventory.removeItemFromSlot(itemSlot, amount);
        player.sub.inventory.addItem(new MaterialItem(materialKey), amount);
        return true;
    }
}
