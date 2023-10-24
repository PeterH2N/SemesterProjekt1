package World;
/* World class for modeling the entire in-game world
 */

import Entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class World {

    final int worldSize = 10; // in tiles
    final int tileSize = 100; // in meters, so the map os 1km x 1km
    final double boundMin, boundMax;

    Tile[][] map = new Tile[worldSize][worldSize];
    Map<Integer, Entity> entites = new HashMap<Integer, Entity>();

    public World() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                map[i][j] = new Tile();

        // set the bounds for the world, outside which entities cannot travel or be created
        double b = worldSize * tileSize / 2.0;
        boundMin = -b;
        boundMax = b;

        createWaste(100);
    }

    // adds an entity to the world, and also appends the ID to the list of the appropriate tile.
    void addEntity() {

    }

    public int[] getItemsWithinRadius(Point p, double radius) {
        // loops over entities, finds the items that are  within the radius with the Point.distance function, and return an array with the IDs
        return new int[]{};
    }

    // creates an amount of wasteitems and places them randomly throughout the ocean. this should be done periodically
    void createWaste(int amount) {

    }

}

class Tile {
    ArrayList<Integer> itemIDs = new ArrayList<>();

    // some day this could have some sort of "environment", like islands.
    Tile() {
    }
}

