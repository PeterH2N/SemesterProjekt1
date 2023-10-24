package Player;

import Entity.Point;

public class Player {
    Submarine sub = new Submarine();

    int balance = 0; // in cents

    Player() {
        balance = 10000; // 100 dollars
    }

    int addToBalance(int p) {
        return (balance += p);
    }

    int removeFromBalance(int m) {
        return (balance -= m);
    }

    void moveTo(Point p) {
        sub.moveTo(p);
    }

    void pickup() {
        sub.pickupItems();
    }
}
