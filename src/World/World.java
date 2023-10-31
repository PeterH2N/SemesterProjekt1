package World;
/* World class for modeling the entire in-game world
 */

import Entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class World {

    final int layers = 3;
    final int worldSize = 10; // meters
    //final double boundMin, boundMax;
    Space[][][] map = new Space[worldSize][worldSize][layers];

    public World() {
        // init all spaces
        for (int i = 0; i < worldSize; i++)
            for (int j = 0; j < worldSize; j++)
                for (int k = 0; k < layers; k++)
                    map[i][j][k] = new Space(true);

        createWaste(100);
    }

    // adds an entity to the world, and also appends the ID to the list of the appropriate tile.
    void addEntity() {

    }

    // creates an amount of wasteitems and places them randomly throughout the ocean. this should be done periodically
    void createWaste(int amount) {

    }

}

