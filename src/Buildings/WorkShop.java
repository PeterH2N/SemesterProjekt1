package Buildings;

import Items.Item;
import Items.MaterialItem;
import Items.WasteItem;
import Items.WasteType;
import Player.*;

public class WorkShop extends Building {
    public WorkShop(){
        super("This is a workshop");
    }

    public boolean upgradeInventoryCapacity(Player player){
        for( int i = 0; i < player.sub.inventory.slots.length; i++){
            Item item = player.sub.inventory.slots[i].item;
            if(item.getName().equals("Glass")) {
                if (player.sub.inventory.removeItem(item.getName(), 3)) {
                    player.sub.upgradeInventorylevel();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean upgradeFuelCapacity(Player player) {
        for (int i = 0; i < player.sub.inventory.slots.length; i++) {
            Item item = player.sub.inventory.slots[i].item;
            if (item.getName().equals("Aluminum")) {
                if (player.sub.inventory.removeItem(item.getName(), 2)) {
                    player.sub.upgradeFuelLevel();
                    return true;
                }
            }
        }
        return false;
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
