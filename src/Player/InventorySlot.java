package Player;

import Items.Item;

public class InventorySlot {
    public Item item;
    public int amount;

    @Override
    public String toString() {
        return item.getName() + " x " + amount;
    }
}
