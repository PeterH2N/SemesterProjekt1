package Buildings;

import Globals.Globals;
import Items.Item;
import Items.MaterialItem;
import Player.Player;

public class Shop extends Building {
    public Shop(String description, String name) {
        super(description, name);
    }

    public boolean sellItem(Player player, int slot, int amount) {
        // if slot is empty
        if (player.sub.inventory.slots[slot] == null)
            return false;
        //if item isn't material item
        if (!(player.sub.inventory.slots[slot].item instanceof MaterialItem))
            return false;
        // if amount is larger than the amount of items in the slot
        if (!(player.sub.inventory.slots[slot].amount < amount))
            return false;
        MaterialItem item = (MaterialItem) player.sub.inventory.slots[slot].item;
        double money = amount * item.getMaterial().pricePerItem;
        player.sub.inventory.removeItem(item.getName(), amount);

        return true;
    }
    public boolean buyFuel(Player player, double amount) {
        double price = Globals.fuelPricePerLitre * amount;

        // early exit
        if (player.getBalance() < price)
            return false;

        player.removeFromBalance((int) price);

        player.sub.refuel(amount);
        return true;
    }
}
