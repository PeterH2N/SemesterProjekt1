package Buildings;

import Entity.*;

public class Building extends Entity {
    String description;
    String name;

    public Building(String description, String name, Point pos) {
        super(pos);
        this.description = description;
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public String getName() {
        return name;
    }

}
