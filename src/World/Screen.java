package World;

import Globals.Globals;
import MapGeneration.MapGenerator;

import java.awt.image.BufferedImage;

public class Screen
{
    public Space[][][] map = new Space[Globals.screenSize][Globals.screenSize][Globals.layers];

    public int x, y;
    public Screen(int xStart, int yStart) {
        x = xStart;
        y= yStart;
        for (int i = 0; i < Globals.screenSize; i++) {
            for (int j = 0; j < Globals.screenSize; j++) {
                for (int k = 0; k < Globals.layers; k++) {
                    map[i][j][k] = new Space(true);

                }
            }
        }

        setTerrain(MapGenerator.makeLayerImage(MapGenerator.makeNoiseImage(xStart, yStart), x + " " + y));
        createWaste(0.1);
    }

    void createWaste(double probability) {
        for (int i = 0; i < Globals.screenSize; i++)
            for (int j = 0; j < Globals.screenSize; j++)
                for (int k = 0; k < Globals.layers; k++) {
                    Space current =  map[i][j][k];
                    // place waste items inside space
                    if (current.available) {
                        if (Math.random() < probability) {
                            current.createWaste(1);
                        }
                    }
                }
    }

    private void setTerrain(BufferedImage img) {
        // this image contains the same amounts of shades of grey as layers in the world, that represent where the height of the terrain.

        for (int y = 0; y < Globals.screenSize; y++) {
            for (int x = 0; x < Globals.screenSize; x++) {
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
