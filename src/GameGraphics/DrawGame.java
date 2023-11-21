package GameGraphics;

import Context.Context;
import Globals.Globals;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.File;

public class DrawGame
{
    public static Canvas canvas;

    public static double pixelsPerTile;

    public static Context context;

    static Image currentLayerImage;
    public static void drawGame() {
        // for now we are just drawing the basic layer image to the screen
        canvas.getGraphicsContext2D().drawImage(currentLayerImage,0, 0, canvas.getWidth(), canvas.getHeight());
    }
    static void setLayerImage() {
        int xStart = context.world.currentScreen.x;
        int yStart = context.world.currentScreen.y;
        String key = xStart + " " + yStart;
        File file = new File(Globals.MapGenPath + "Images/layer" + key + ".png");
        Image img = new Image(file.toURI().toString());
        currentLayerImage = img;
    }
}
