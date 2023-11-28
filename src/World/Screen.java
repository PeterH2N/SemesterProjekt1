package World;

import Entity.*;
import Globals.Globals;
import Items.WasteItem;
import Items.WasteType;
import MapGeneration.MapGenerator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Screen
{
    public Space[][][] map = new Space[Globals.tilesPerScreen][Globals.tilesPerScreen][Globals.layers];
    public ArrayList<Entity> entities = new ArrayList<>();

    public int x, y;
    public Screen(int xStart, int yStart) {
        x = xStart;
        y= yStart;
        for (int i = 0; i < Globals.tilesPerScreen; i++) {
            for (int j = 0; j < Globals.tilesPerScreen; j++) {
                for (int k = 0; k < Globals.layers; k++) {
                    map[i][j][k] = new Space(true);

                }
            }
        }

        setTerrain(MapGenerator.makeLayerImage(MapGenerator.makeNoiseImage(xStart, yStart), x + " " + y));
        createWaste(20, 0);
    }

    void createWaste(int amount, int layer) {

        for (int i = 0; i < amount; i++) {
            // get random waste type
            String randomKey = "default";
            while (randomKey.equals("default")) {
                int randomIndex = (int) (Math.random() * (WasteType.wasteTypeKeys.size() - 1));
                randomKey = WasteType.wasteTypeKeys.get(randomIndex);
            }
            // random point within "screensize"
            boolean available = false;
            double ix = 0;
            double iy = 0;
            while (!available) {
                ix = Math.random() * (Globals.tilesPerScreen - 1);
                iy = Math.random() * (Globals.tilesPerScreen - 1);
                available = map[(int)ix][(int)ix][layer].available;
            }
            double rotation = Math.random() * 360.0;
            Entity e = new WasteItem(randomKey, new Point(ix, iy));
            e.setRotation(rotation);
            entities.add(e);
        }

    }

    private void setTerrain(BufferedImage img) {
        // this image contains the same amounts of shades of grey as layers in the world, that represent where the height of the terrain.

        for (int y = 0; y < Globals.tilesPerScreen; y++) {
            for (int x = 0; x < Globals.tilesPerScreen; x++) {
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
