package Player;

import Items.Item;
import Items.WasteItem;
import java.util.ArrayList;

public class Inventory {
    public InventorySlot[] slots;

    Inventory(int capacity) {
        slots = new InventorySlot[capacity];
    }

    //increases capacity of the inventory
    void increaseCapacity(int nCapacity) {
        InventorySlot[] newSlots = new InventorySlot[nCapacity];

        System.arraycopy(slots, 0, newSlots, 0, slots.length);

        slots = newSlots;
    }

    public void printInventory() {
        for (int i = 0; i < slots.length; i++) {
            System.out.print((i + 1) + ": ");
            if (slots[i] != null ) {
                Item item = slots[i].item;
                if (item != null) {
                    System.out.print(item.getName() + " x " + slots[i].amount);
                }
            }
            System.out.print("\n");

        }
    }

    // adds item to inventory. If the itemtype already exists, add the amount of items. Else, add the item to new slot
    // returns index to item, or -1 if inventory is full
    public int addItem(Item item, int amount) {;
        // loop through items, if we have one of the same type, we add to the amount
        int overflowAmount;
        for (InventorySlot slot : slots) {
            if (slot == null) {
                continue;
            }
            if (slot.item.getName().equals(item.getName())) {
                if (slot.amount + amount > item.getStackSize()) {
                    overflowAmount = amount + item.getStackSize() - slot.amount;
                    slot.amount += amount - overflowAmount;
                    amount = overflowAmount;
                }
                else {
                    slot.amount += amount;
                    return 1;
                }

            }
        }
        // find the first empty slot
        if (amount > 0) {
            for (int i = 0; i < slots.length; i++) {
                if (slots[i] == null) {
                    slots[i] = new InventorySlot();
                    slots[i].item = item;
                    slots[i].amount = amount;

                    return 1;
                }
            }
        }
        // if all this fails, the inventory is full
        return -1;
    }

    public int addItem(Item item) {
        return addItem(item, 1);
    }

    // return 1 if successful, -1 otherwise
    public boolean removeItem(String itemName, int amount) {
        ArrayList<Integer> indices = new ArrayList<>();
        int sumAmount = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i]==null)
                continue;
            Item slotItem = slots[i].item;
            int slotAmount = slots[i].amount;
            if (slotItem.getName().equals(itemName)) {
                sumAmount += slotAmount;
                indices.add(i);
            }
        }
        if (sumAmount >= amount) {
            for (int i = indices.size() - 1; i >= 0; i--) {
                if (amount <= 0)
                    break;

                int oldAmount = slots[indices.get(i)].amount;
                slots[indices.get(i)].amount -= amount;
                amount -= oldAmount;
                if (slots[indices.get(i)].amount <= 0)
                    slots[indices.get(i)] = null;
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean containsItemKey(String key) {
    return false;
    }
}

