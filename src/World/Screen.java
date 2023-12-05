package World;

import Buildings.WorkShop;
import Entity.*;
import Globals.Globals;
import Items.WasteItem;
import Items.WasteType;
import MapGeneration.MapGenerator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Screen
{
    public Space[][][] map = new Space[Globals.tilesPerScreen][Globals.tilesPerScreen][Globals.layers];
    public ArrayList<Entity> entities = new ArrayList<>();

    Random rand;

    public int x, y;
    public Screen(int xStart, int yStart, Random rand) {
        this.rand = rand;
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
        createWaste((int)(Globals.tilesPerScreen * 1.5), 0);
        //entities.add(new WorkShop(new Point(5,5)));
        makeWorkshop();
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

    private void makeWorkshop() {
        // every time we load a screen, we have a 50% chance of spawning a workshop
        if (rand.nextDouble() >= Globals.workShopSpawnRate) {
            return;
        }

        // find all grass spaces that are directly next to water
        ArrayList<Point> candidates = new ArrayList<>();
        for (int y = 0; y < Globals.tilesPerScreen; y++) {
            for (int x = 0; x < Globals.tilesPerScreen; x++) {
                Point current = new Point(x, y);
                // if space is not grass, continue
                if (map[y][x][0].available) {
                    continue;
                }
                // check for water around the space
                if (waterNextTo(x, y))
                    candidates.add(current);
            }

        }

        int index = (int) (rand.nextDouble() * candidates.size());
        Point pick = candidates.get(index);
        Point pos = new Point(pick.x + 0.5, pick.y + 0.5);
        entities.add(new WorkShop(pos));
    }

    private boolean waterNextTo(int x, int y)
    {
        // north
        if (y > 0) {
            if (map[y - 1][x][0].available)
                return true;
        }
        // south
        if (y < Globals.tilesPerScreen - 1)
            if (map[y + 1][x][0].available)
                return true;
        // west
        if (x > 0)
            if (map[y][x - 1][0].available)
                return true;
        // east
        if (x < Globals.tilesPerScreen - 1)
            if (map[y][x + 1][0].available)
                return true;

        return false;
    }
}
