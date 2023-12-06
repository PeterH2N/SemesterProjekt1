package GameGraphics;

import Buildings.Building;
import Buildings.Shop;
import Buildings.WorkShop;
import Context.Context;
import Entity.Entity;
import Globals.Globals;
import Items.*;
import MapGeneration.MapGenerator;
import World.Screen;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.HashMap;

public class DrawGame
{
    public static Canvas canvas;

    public static double pixelsPerTile;

    public static Context context;

    static Image currentLayerImage;
    static Image playerImage;
    static HashMap<String, Image> wasteTypeImages = new HashMap<>();
    static Image grassImage;
    static Image grassEdgeTopImage;
    static Image grassEdgeLeftImage;
    static Image grassEdgeBottomImage;
    static Image grassEdgeRightImage;
    static Image stoneImage;
    static Image waterImage;

    static Image workShopImage;

    static Image shopImage;

    static {
        // get player sprite
        File file = new File(Globals.spritePath + "sub.png");
        playerImage = new Image(file.toURI().toString());
        // get grass sprites
        file = new File(Globals.spritePath + "Surfaces/Grass/grass.png");
        grassImage = new Image(file.toURI().toString());
        file = new File(Globals.spritePath + "Surfaces/Grass/grassEdgeTop.png");
        grassEdgeTopImage = new Image(file.toURI().toString());
        file = new File(Globals.spritePath + "Surfaces/Grass/grassEdgeLeft.png");
        grassEdgeLeftImage = new Image(file.toURI().toString());
        file = new File(Globals.spritePath + "Surfaces/Grass/grassEdgeBottom.png");
        grassEdgeBottomImage = new Image(file.toURI().toString());
        file = new File(Globals.spritePath + "Surfaces/Grass/grassEdgeRight.png");
        grassEdgeRightImage = new Image(file.toURI().toString());

        // get stone sprite
        file = new File(Globals.spritePath + "Surfaces/Stone/stone.png");
        stoneImage = new Image(file.toURI().toString());

        // get water sprite
        file = new File(Globals.spritePath + "Surfaces/Water/water.png");
        waterImage = new Image(file.toURI().toString());

        // get building sprites
        file = new File(Globals.spritePath + "Buildings/WorkShop.png");
        workShopImage = new Image(file.toURI().toString());

        file = new File(Globals.spritePath + "Buildings/Shop.png");
        shopImage = new Image(file.toURI().toString());

        // get waste item type images
        for (String typeKey : WasteType.wasteTypeKeys) {
            String path = Globals.spritePath + "WasteTypes/" + typeKey + ".png";
            file = new File(path);
            Image img = new Image(file.toURI().toString());
            wasteTypeImages.put(typeKey, img);
        }
    }
    public static void drawGame() {
        // clear screen
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setGlobalAlpha(1.0);
        gc.drawImage(currentLayerImage,0, 0, canvas.getWidth(), canvas.getHeight());
        if (DrawGame.context.player.sub.z == 0) {
            drawGrass();
        }
        else {
            drawStone(DrawGame.context.player.sub.z);
            gc.setGlobalAlpha(0.5);
            gc.setFill(Color.BLUE);
            gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        }

        drawEntities(DrawGame.context.player.sub.z);
        drawPlayer();
    }

    static void drawEntities(int layer) {
        Screen currentScreen = context.world.currentScreen;

        // draw items, if there are any
        for (Entity entity : currentScreen.entities[layer]) {
            if (entity instanceof WasteItem) {
                drawWasteItem((WasteItem) entity);
            }
            else if (entity instanceof Building) {
                drawBuilding((Building) entity);
            }
        }

    }

    static void drawBuilding(Building building) {
        Image image = null;
        if (building instanceof WorkShop)
            image = workShopImage;
        else if (building instanceof Shop)
            image = shopImage;
        else
            return;

        double x = building.getPosition().x;
        double y = building.getPosition().y;

        canvas.getGraphicsContext2D().drawImage(image, x * pixelsPerTile - pixelsPerTile * 0.5, y * pixelsPerTile - pixelsPerTile * 0.5, pixelsPerTile, pixelsPerTile);
    }

    static void drawWasteItem(WasteItem item) {

        String key = item.getName();

        double imageScale = WasteType.wasteTypes.get(key).getImageScale();
        double scale = pixelsPerTile * imageScale;

        double dx = item.getPosition().x * pixelsPerTile - pixelsPerTile * 0.5;
        double dy = item.getPosition().y * pixelsPerTile - pixelsPerTile * 0.5;

        // rotate image
        ImageView iv = new ImageView(wasteTypeImages.get(key));
        iv.setRotate(item.getRotation());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        canvas.getGraphicsContext2D().drawImage(rotatedImage, dx + scale * 0.5, dy + scale * 0.5, scale, scale);

    }

    static void drawPlayer() {
        // get position to draw to
        double subX = context.player.sub.x;
        double subY = context.player.sub.y;

        double x = subX * pixelsPerTile;
        double y = subY * pixelsPerTile;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double scale = pixelsPerTile * 0.4;

        // draw with proper rotation
        ImageView iv = new ImageView(playerImage);
        iv.setRotate(context.player.sub.getRotation());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null); // maybe this solution is not the best, but it works
        gc.drawImage(rotatedImage, x - scale * 0.5, y - scale * 0.5, pixelsPerTile + scale, pixelsPerTile + scale);

    }

    static void drawStone(int currentLayer) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // get top layer color
        java.awt.Color awtc = MapGenerator.layerColor[currentLayer];
        Color glc = Color.rgb(awtc.getRed(), awtc.getGreen(), awtc.getBlue());

        // loop through layer image, find all the grass tiles
        for (int y = 0; y < Globals.tilesPerScreen; y++) {
            for (int x = 0; x < Globals.tilesPerScreen; x++) {
                double dx = x * pixelsPerTile;
                double dy = y * pixelsPerTile;

                if (currentLayerImage.getPixelReader().getColor(x, y).getBlue() >= glc.getBlue()) {
                    gc.setGlobalAlpha(0.8);
                }
                else {
                    gc.setGlobalAlpha(0.3);
                }
                // draw the stone layer
                gc.drawImage(stoneImage, dx, dy, pixelsPerTile, pixelsPerTile);

            }
        }
    }

    static void drawGrass() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // get the lightest color, grass layer color
        java.awt.Color awtc = MapGenerator.layerColor[0];
        Color glc = Color.rgb(awtc.getRed(), awtc.getGreen(), awtc.getBlue());



        // loop through layer image, find all the grass tiles
        for (int y = 0; y < Globals.tilesPerScreen; y++) {
            for (int x = 0; x < Globals.tilesPerScreen; x++) {
                double dx = x * pixelsPerTile;
                double dy = y * pixelsPerTile;
                if (currentLayerImage.getPixelReader().getColor(x, y).equals(glc)) {
                    gc.setGlobalAlpha(1.0);
                    // draw the bottom grass layer
                    gc.drawImage(grassImage, dx, dy, pixelsPerTile, pixelsPerTile);
                    // test whether each edge should be drawn
                    // top
                    if (y > 0) {
                        if (!currentLayerImage.getPixelReader().getColor(x, y - 1).equals(glc)) {
                            gc.drawImage(grassEdgeTopImage, dx, dy, pixelsPerTile, pixelsPerTile);
                        }
                    }
                    // left
                    if (x > 0) {
                        if (!currentLayerImage.getPixelReader().getColor(x - 1, y ).equals(glc)) {
                            gc.drawImage(grassEdgeLeftImage, dx, dy, pixelsPerTile, pixelsPerTile);
                        }
                    }
                    // bottom
                    if (y < Globals.tilesPerScreen - 1) {
                        if (!currentLayerImage.getPixelReader().getColor(x, y + 1).equals(glc)) {
                            gc.drawImage(grassEdgeBottomImage, dx, dy, pixelsPerTile, pixelsPerTile);
                        }
                    }
                    // right
                    if (x < Globals.tilesPerScreen - 1) {
                        if (!currentLayerImage.getPixelReader().getColor(x + 1, y).equals(glc)) {
                            gc.drawImage(grassEdgeRightImage, dx, dy, pixelsPerTile, pixelsPerTile);
                        }
                    }
                }
                else {
                    gc.setGlobalAlpha(0.3);
                    //gc.drawImage(stoneImage, dx, dy, pixelsPerTile, pixelsPerTile);
                    gc.drawImage(waterImage, dx, dy, pixelsPerTile, pixelsPerTile);

                }
            }
        }
        gc.setGlobalAlpha(1.0);

    }
    static void setLayerImage() {
        int xStart = context.world.currentScreen.x;
        int yStart = context.world.currentScreen.y;
        String key = xStart + " " + yStart;
        File file = new File(Globals.mapGenPath + "Images/layer" + key + ".png");
        Image img = new Image(file.toURI().toString());
        currentLayerImage = img;
    }
}
