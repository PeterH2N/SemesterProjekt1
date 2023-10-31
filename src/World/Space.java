package World;
import Entity.*;

import java.util.AbstractList;
import java.util.ArrayList;

public class Space
{
    ArrayList<Entity> entities = new ArrayList<>();

    final boolean available;

    Space(boolean a) {
        available = a;
    }
    Space() {
        available = true;
    }
}
