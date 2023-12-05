package Player;

import Globals.Globals;
import World.*;

public class Player {
    public Submarine sub = new Submarine();

    int balance; // in cents

    public Player() {
        balance = 10000; // 100 dollars
    }

    int addToBalance(int p) {
        return (balance += p);
    }

    int removeFromBalance(int m) {
        return (balance -= m);
    }

    public boolean move(String dir, World world) {
        if (sub.move(dir, world)) {
            sub.fuel -= Globals.fuelConsumptionPerMove;
            return true;
        }
         return false;
    }

    public void pickUp(Screen screen) {
        sub.pickupItems(screen);
        sub.fuel -= Globals.fuelConsumptionPerPickUp;
    }

    public void deleteItem(int slotIndex, int amount) {
        if (sub.inventory.slots[slotIndex] == null)
            return;
        String itemName = sub.inventory.slots[slotIndex].item.getName();
        sub.inventory.removeItem(itemName, amount);
    }

    public int[] getPosition() {
        int[] p = new int[3];
        p[0] = sub.x;
        p[1] = sub.y;
        p[2] = sub.z;

        return p;
    }

    public void printPosition()
    {
        System.out.println("(" + sub.x + "," + sub.y + "), Layer " + sub.z);
    }

}
