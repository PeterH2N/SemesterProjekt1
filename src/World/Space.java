package World;
import Buildings.WorkShop;
import Entity.*;
import Items.*;

import java.util.AbstractList;
import java.util.ArrayList;

public class Space
{
    public ArrayList<Entity> entities = new ArrayList<>();

    final boolean available;

    Space(boolean a) {
        available = a;
    }
    Space() {
        available = true;
    }

    void createWaste(int amount) {
        // get random waste type
        for (int i = 0; i < amount; i++) {
            String randomKey = "default";
            while (randomKey.equals("default")) {
                int randomIndex = (int) (Math.random() * (WasteType.wasteTypeKeys.size() - 1));
                randomKey = WasteType.wasteTypeKeys.get(randomIndex);
            }
            Entity e = new WasteItem(randomKey);
            entities.add(e);
        }

    }
    void createWorkShop(){
        Entity w = new WorkShop();
        entities.add(w);
    }


}
