package Buildings;

import Entity.*;

public class Building extends Entity {
    String desription;

    public Building(String description) {
        this.desription = desription;
    }
    public String getDesription(){
        return desription;
    }

}
