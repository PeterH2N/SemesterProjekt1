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

                }
        int xStart = 3;
        int yStart = 7;

        setTerrain(MapGenerator.makeLayerImage(MapGenerator.makeNoiseImage(xStart, yStart), "layers" + xStart + yStart));
        createWaste(10);

    }

    void createWaste(int maxPerSpace) {
        for (int i = 0; i < Globals.worldSize; i++)
            for (int j = 0; j < Globals.worldSize; j++)
                for (int k = 0; k < Globals.layers; k++) {
                    Space current =  map[i][j][k];
                    // place waste items inside space
                    if (current.available) {
                        int amount = (int) (Math.random() * (maxPerSpace + 1));
                        current.createWaste(amount);
                    }
                }
    }

    private void setTerrain(BufferedImage img) {
        // this image contains the same amounts of shades of grey as layers in the world, that represent where the height of the terrain.

        for (int y = 0; y < Globals.worldSize; y++) {
            for (int x = 0; x < Globals.worldSize; x++) {
                // if the color matches the layer
                for (int l = 0; l < Globals.layers; l++) {
                    if (img.getRGB(x, y) == MapGenerator.layerColor[l].getRGB()) {
                        // set this layer and all under it to unavailable
                        for (int i = l; i < Globals.layers; i++) {
                            map[y][x][i].available = false;
                        }
                        break;
                    }
                }
            }
        }
    }

}

