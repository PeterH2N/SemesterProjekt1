package Player;

import Globals.Globals;
import World.*;

public class Player {
    public Submarine sub = new Submarine();

    int balance = 0; // in cents

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

    public void pickUp(Space space) {
        sub.pickupItems(space);
        sub.fuel -= Globals.fuelConsumptionPerPickUp;
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
