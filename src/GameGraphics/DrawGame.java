package GameGraphics;

import Context.Context;
import Entity.Entity;
import Globals.Globals;
import Items.WasteItem;
import World.Screen;
import World.Space;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.io.File;
import java.util.Random;

public class DrawGame
{
    public static Canvas canvas;

    public static double pixelsPerTile;

    public static Context context;

    static Image currentLayerImage;
    static Image playerImage;
    static Image bottleImage;
    static double currentPlayerRotation = 0;

    static {
        // get player sprite
        File file = new File(Globals.spritePath + "sub.png");
        playerImage = new Image(file.toURI().toString());
        // get bottle sprite
        file = new File(Globals.spritePath + "bottle.png");
        bottleImage = new Image(file.toURI().toString());
    }
    public static void drawGame() {
        // for now, we are just drawing the basic layer image to the screen
        canvas.getGraphicsContext2D().drawImage(currentLayerImage,0, 0, canvas.getWidth(), canvas.getHeight());
        drawWasteItems();
        drawPlayer();
    }

    static void drawWasteItems() {

        Random rand = new Random();
        int seed = ((context.world.currentX ^ context.world.currentY) << 8);
        // loop through all spaces in current screen
        Screen currentScreen = context.world.currentScreen;
        for (int y = 0; y < Globals.screenSize; y++) {
            for (int x = 0; x < Globals.screenSize; x++) {
                    Space currentSpace = currentScreen.map[y][x][context.player.sub.z];
                    if (!currentSpace.available)
                        continue;

                    // draw items, if there are any
                    for (Entity entity : currentSpace.entities) {
                        if (entity instanceof WasteItem) {
                            drawWasteItem((WasteItem)entity, x, y, seed, rand);
                        }
                    }
            }
        }
    }

    static void drawWasteItem(WasteItem item, int x, int y, int seed, Random rand) {
        // in the future, change sprite dependent on the kind of waste
        // rotate randomly (but the same every time)
        rand.setSeed(seed ^ (item.getID()  << 16) ^ 0xff6e8 );
        ImageView iv = new ImageView(bottleImage);
        iv.setRotate(rand.nextDouble() * 360.0);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null); // maybe this solution is not the best, but it works

        // offset by some amount to make the location appear more random
        double dx = x * pixelsPerTile + pixelsPerTile * (rand.nextDouble() - 0.4);
        double dy = y * pixelsPerTile + pixelsPerTile * (rand.nextDouble() - 0.4);



        canvas.getGraphicsContext2D().drawImage(rotatedImage, dx, dy, pixelsPerTile, pixelsPerTile);

    }

    static void drawPlayer() {
        // get position to draw to
        double subX = context.player.sub.x;
        double subY = context.player.sub.y;

        double x = subX * pixelsPerTile;
        double y = subY * pixelsPerTile;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // draw with proper rotation
        ImageView iv = new ImageView(playerImage);
        iv.setRotate(currentPlayerRotation);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null); // maybe this solution is not the best, but it works
        gc.drawImage(rotatedImage, x, y, pixelsPerTile, pixelsPerTile);

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
