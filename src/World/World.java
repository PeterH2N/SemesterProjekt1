package World;
/* World class for modeling the entire in-game world
 */

import Entity.*;
import Globals.Globals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class World {

    //final double boundMin, boundMax;
    public Space[][][] map = new Space[Globals.worldSize][Globals.worldSize][Globals.layers];

    public World() {
        // init all spaces
        for (int i = 0; i < Globals.worldSize; i++)
            for (int j = 0; j < Globals.worldSize; j++)
                for (int k = 0; k < Globals.layers; k++) {
                    map[i][j][k] = new Space(true);
                    Space current =  map[i][j][k];
                    // place waste items inside space
                    int amount = (int)(Math.random() * 10/*between 0 and 9*/);
                    current.createWaste(amount);
                }
        Space spawn = map[Globals.worldSize/2][Globals.worldSize/2][0];
        spawn.createWorkShop();

    }

    // adds an entity to the world, and also appends the ID to the list of the appropriate tile.
    void addEntity() {

    }

    // creates an amount of wasteitems and places them randomly throughout the ocean. this should be done periodically
    void createWaste(int amount) {

    }

}

