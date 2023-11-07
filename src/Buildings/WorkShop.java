package Buildings;

import Items.Item;
import Player.*;

public class WorkShop extends Building {
    public WorkShop(){
        super("This is a workshop");
    }

    public boolean upgradeInventoryCapacity(Player player){
        for( int i = 0; i < player.sub.inventory.slots.length; i++){
            Item item = player.sub.inventory.slots[i].item;
            if(item.getName().equals("Glass Bottle")) {
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
            if (item.getName().equals("ALU can")) {
                if (player.sub.inventory.removeItem(item.getName(), 2)) {
                    player.sub.upgradeFuelLevel();
                    return true;
                }
            }
        }
        return false;
    }

}
