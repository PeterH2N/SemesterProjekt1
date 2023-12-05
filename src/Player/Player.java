package Player;

import Globals.Globals;
import World.*;
import Context.*;

public class Player {
    public Submarine sub = new Submarine();

    int balance; // in cents

    public Player() {
        balance = 10000; // 100 dollars
    }

    public int getBalance() {
        return balance;
    }

    public int addToBalance(int p) {
        return (balance += p);
    }

    public int removeFromBalance(int m) {
        return (balance -= m);
    }

    public boolean move(String dir, Context context) {
        if (sub.move(dir, context.world)) {
            sub.fuel -= Globals.fuelConsumptionPerMove;

            //If The player is underwater
            if (sub.z>0) {
                sub.oxygen -= Globals.oxygenConsumptionPerMove;
            }
            //If the player runs out of oxygen
            if (sub.oxygen <0 ) {
                context.makeDone();
            }
            //If The player is on the surface layer, oxygen must be refilled
            if (sub.z == 0) {
                sub.oxygen = sub.getOxygenCapacity();
            }
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

        sub.inventory.removeItemFromSlot(slotIndex, amount);
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
