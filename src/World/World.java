package World;
/* World class for modeling the entire in-game world
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class World {

    // keys are just x and y values for where the screen is, first screen is at (0,0)
    public int currentX;
    public int currentY;
    public Screen currentScreen;
    Map<String, Screen> screens = new HashMap<>();

    Random rand = new Random();

    public World() {
        // Make first screen
        currentX = 0;
        currentY = 0;
        generateScreen(currentX, currentY);
        // move to this screen, so the four adjacent screens are generated
        moveToScreen(currentX, currentY);

        rand.setSeed(10843823 ^ 0x5f9ec9);
    }

    public Screen getAdjacentScreen(String dir) {
        if (dir.equals("east")) {
            return screens.get((currentX + 1) + " " + currentY);
        }
        if (dir.equals("west")) {
            return screens.get((currentX - 1) + " " + currentY);
        }
        if (dir.equals("north")) {
            return screens.get(currentX + " " + (currentY - 1));
        }
        if (dir.equals("south")) {
            return screens.get(currentX + " " + (currentY + 1));
        }
        return null;
    }

    public void moveToAdjacentScreen(String dir) {
        if (dir.equals("east")) {
            moveToScreen(currentX + 1, currentY);
        }
        if (dir.equals("west")) {
            moveToScreen(currentX - 1, currentY);
        }
        if (dir.equals("north")) {
            moveToScreen(currentX, currentY - 1);
        }
        if (dir.equals("south")) {
            moveToScreen(currentX, currentY + 1);
        }
    }

    public void generateScreen(int x, int y) {
        String key = x + " " + y;
        // if screen already exists, we return.
        if (screens.containsKey(key)) {
            return;
        }

        Screen newScreen = new Screen(x, y, rand);
        screens.put(key, newScreen);

    }
    void moveToScreen(int x, int y) {
        // as we always generate the adjacent screens, we should never be able to move into a null screen.
        currentX = x;
        currentY = y;
        String key = x + " " + y;
        currentScreen = screens.get(key);
        // make the 4 adjacent screens
        // north
        generateScreen(x, y - 1);

        // east
        generateScreen(x + 1, y);

        // south
        generateScreen(x, y + 1);

        // west
        generateScreen(x - 1, y);
    }

}

