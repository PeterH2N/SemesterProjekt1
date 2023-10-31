package World;
/* World class for modeling the entire in-game world
 */

import Globals.Globals;

import java.awt.*;
import java.awt.image.BufferedImage;
import MapGeneration.*;

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

        setTerrain(MapGenerator.makeLayerImage(MapGenerator.makeNoiseImage(1, 1), "layer"));

    }

    private void setTerrain(BufferedImage img) {
        // this image contains the same amounts of shades of grey as layers in the world, that represent where the height of the terrain.
        double increment = 1.0 / (double)Globals.layers;
        for (int i = Globals.layers; i > 0; i--) {
            float e = (float)increment * i;
            Color color = new Color(e, e, e);
            for (int y = 0; y < Globals.worldSize; y++) {
                for (int x = 0; x < Globals.worldSize; x++) {
                    // if the color matches the layer
                    int rgb = img.getRGB(x, y);
                    if (img.getRGB(x, y) == color.getRGB()) {
                        // make the space, and all spaces underneath it unavailable
                        for (int d = Globals.layers - i; d < Globals.layers; d++) {
                            map[y][x][d].available = false;
                        }
                    }
                }
            }
        }
    }

}

