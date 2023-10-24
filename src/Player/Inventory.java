package Player;

import Items.Item;

public class Inventory {
    InventorySlot[] items;

    Inventory(int capacity) {
        items = new InventorySlot[capacity];
    }

    //increases capacity of the inventory
    void increaseCapacity(int nCapacity) {

    }

    void printInventory() {

    }

    // adds item to inventory. If the itemtype already exists, add the amount of items. Else, add the item to new slot
    // returns index to item, or -1 if inventory is full
    public int addItem(Item item, int amount) {
        return -1;
    }

    public int addItem(Item item) {
        return addItem(item, 1);
    }
}

class InventorySlot {
    Item item;
    int amount;
}
