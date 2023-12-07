package Buildings;

import Entity.Point;
import Globals.Globals;
import Items.MaterialItem;
import Items.WasteItem;
import Player.*;

public class WorkShop extends Building {
    public WorkShop(Point pos){
        super("This is a workshop", "Workshop", pos);
    }

    public boolean upgrade(String upgrade, Player player) {
        if (Globals.upgrades.get(upgrade) == null){
            Globals.globalMessage = "Not a valid upgrade!";
            return false;
        }

        // check if we are fully upgraded
        if (player.sub.getInventoryCapacityLevel() == Globals.upgrades.get(upgrade).length - 1) {
            Globals.globalMessage = "Inventory is already fully upgraded!";
            return false;
        }
        // make sure we have all the items needed
        for (int i = 0; i < Globals.upgradeItemAmount.get(upgrade).length; i++) {
            if (!player.sub.inventory.containsItems(Globals.upgradeItemName.get(upgrade)[i], Globals.upgradeItemAmount.get(upgrade)[i][player.sub.getInventoryCapacityLevel()])) {
                Globals.globalMessage = ("You dont have the required items! You need: \n");
                for (int j = 0; j < Globals.upgradeItemName.get(upgrade).length; j++){
                    Globals.globalMessage +=
                            Globals.upgradeItemName.get(upgrade)[j] + " x " +
                                    Globals.upgradeItemAmount.get(upgrade)[j][player.sub.getInventoryCapacityLevel()] + "\n";
                }
                return false;
            }
        }
        // now we remove the items
        for (int i = 0; i < Globals.upgradeItemName.get(upgrade).length; i++) {
            String itemName = Globals.upgradeItemName.get(upgrade)[i];
            int amount = Globals.upgradeItemAmount.get(upgrade)[i][player.sub.getInventoryCapacityLevel()];
            player.sub.inventory.removeItem(itemName, amount);
        }
        // lastly, we upgrade
        player.sub.upgrade(upgrade);
        Globals.globalMessage = "Upgraded " + upgrade + "!";
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
