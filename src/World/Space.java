package World;
import Buildings.WorkShop;
import Entity.*;
import Items.*;

import java.util.AbstractList;
import java.util.ArrayList;

public class Space
{
    public boolean available;

    public boolean animalPresent;

    Space(boolean a) {
        available = a;
    }
    Space() {
        available = true;
    }


    void createWorkShop(){
        Entity w = new WorkShop(new Point());
        //entities.add(w);
    }

    void setAnimalPresent(){
        animalPresent = true;
    }


}
