package MapGeneration;

import Globals.Globals;
import MapGeneration.Simplex.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MapGenerator
{
    static int width = Globals.tilesPerScreen;
    static int height = Globals.tilesPerScreen;

    static double freq1 = 1;
    static double freq2 = 2;
    static double freq3 = 4;

    static double amp1 = 1.5;
    static double amp2 = 0.25;
    static double amp3 = 0.125;

    public static Color[] layerColor = new Color[Globals.layers + 1];
    static double increment;

    static {
        setLayerColors();
    }
    static double noise(double xin, double yin) {
        return (SimplexNoise.noise(xin, yin) + 1.0) * 0.5;
    }

    static public BufferedImage makeNoiseImage() {
        return makeNoiseImage(0, 0);
    }
    static public BufferedImage makeNoiseImage(int xStart, int yStart) {
        return makeNoiseImage(xStart, yStart, Globals.mapGenPath + "Images/noise");
    }

    static public BufferedImage makeNoiseImage(int xStart, int yStart, String path) {

        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Random random = new Random();
        random.setSeed(24090494);
        double rand = random.nextDouble();
        // do noise things
        for (int y = yStart * height; y < height + yStart * height; y++) {
            for (int x = xStart * width; x < width + xStart * width; x++) {
                double xin = x / (double)width - rand;
                double yin = y / (double)height - rand;
                // noise is between -1;1, so we scale it
                double n = 0;
                n +=  amp1 * noise( freq1 * xin, freq1 * yin);
                n += amp2 * noise(freq2 * xin, freq2 * yin);
                n += amp3 * noise(freq3 * xin, freq3 * yin);
                // divide by sum of amplitudes
                n /= (amp1 + amp2 + amp3);
                //n = Math.pow(n, 1);
                float e = (float)n;
                Color color = new Color(e, e, e);
                temp.setRGB(x - xStart * width, y - yStart * height, color.getRGB());
            }
        }

        /*File of = new File(path + Integer.toString(xStart) + Integer.toString(yStart) + ".png");
        try {
            ImageIO.write(temp, "png", of);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }*/
        return temp;
    }

    static void setLayerColors() {
        int highest = 255;
        int lowest = 0;

        // adjustments
        highest -= 30;
        lowest += 20;

        int layers = Globals.layers + 1;
        increment = (double)(highest - lowest) / (double)(layers - 1);

        for (int i = 0; i < layerColor.length; i++) {
            int c = (int)(lowest + increment * i);
            layerColor[layerColor.length - i - 1] = new Color(10, 10, c);
        }
    }
    static public BufferedImage makeLayerImage(BufferedImage img, String key) {
        if (img.getHeight() != Globals.tilesPerScreen || img.getWidth() != Globals.tilesPerScreen) {
            System.out.println("Image not of correct size!");
            return null;
        }

        BufferedImage tmp = new BufferedImage(Globals.tilesPerScreen, Globals.tilesPerScreen, BufferedImage.TYPE_INT_RGB);

         // loop through pixels in image
         for (int y = 0; y < img.getHeight(); y++) {
             for (int x = 0;  x < img.getWidth(); x++) {
                 for (Color lc : layerColor) {
                     int ic = -img.getRGB(x, y) & 0xff;
                     int ilc = -lc.getRGB() & 0xff;
                     if (Math.abs(ic - ilc) < increment) {
                         tmp.setRGB(x, y, lc.getRGB());
                         break;
                     }
                 }
             }
         }
        File of = new File(Globals.mapGenPath + "Images/layer" + key + ".png");
        try {
            ImageIO.write(tmp, "png", of);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }
}
