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
            System.out.println("Inventory is already fully upgraded!");
            return false;
        }
        // make sure we have all the items needed
        for (int i = 0; i < Globals.inventoryUpgradeItemAmount.length; i++) {
            if (!player.sub.inventory.containsItems(Globals.inventoryUpgradeItemName[i], Globals.inventoryUpgradeItemAmount[i][player.sub.getInventoryCapacityLevel()])) {
                System.out.println("You dont have the required items!");
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
        return true;
    }

    public boolean upgradeFuelCapacity(Player player) {
        // check if already fully upgraded
        if (player.sub.getFuelCapacityLevel() == Globals.fuelUpgrades.length - 1) {
            System.out.println("Fuel capacity is already fully upgraded!");
            return false;
        }
        // make sure we have all the items needed
        for (int i = 0; i < Globals.fuelUpgradeItemName.length; i++) {
            if (!player.sub.inventory.containsItems(Globals.fuelUpgradeItemName[i], Globals.fuelUpgradeItemAmount[i][player.sub.getFuelCapacityLevel()])) {
                System.out.println("You dont have the required items!");
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
        return true;
    }
    public boolean smelt(Player player, int itemSlot) {
        InventorySlot slot = player.sub.inventory.slots[itemSlot];

        if (slot==null){
            return false;
        }
        if (!(slot.item instanceof Items.WasteItem)) {
            return false;
        }
        WasteItem waste = (Items.WasteItem) slot.item;
        String materialKey = waste.getMaterialKey();

        int materialAmount = slot.amount;

        player.sub.inventory.removeItem(slot.item.getName(), materialAmount);
        player.sub.inventory.addItem(new MaterialItem(materialKey), materialAmount);
        return true;
    }
}
