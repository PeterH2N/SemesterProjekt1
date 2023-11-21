package World;
/* World class for modeling the entire in-game world
 */

import Globals.Globals;
import java.util.HashMap;
import java.util.Map;

public class World {

    // keys are just x and y values for where the screen is, first screen is at (0,0)
    public Screen currentScreen;
    Map<String, Screen> screens = new HashMap<String, Screen>();

    public World() {
        // Make first screen
        int x = 0;
        int y = 0;
        generateScreen(x, y);
        // move to this screen, so the four adjacent screens are generated
        moveToScreen(x, y);

        //Space spawn = map[Globals.worldSize / 2][Globals.worldSize / 2][0];
        //spawn.createWorkShop();
    }

    public void generateScreen(int x, int y) {
        String key = x + " " + y;
        // if screen already exists, we return.
        if (screens.containsKey(key)) {
            return;
        }

        Screen newScreen = new Screen(x, y);
        screens.put(key, newScreen);

    }
    public void moveToScreen(int x, int y) {
        // as we always generate the adjacent screens, we should never be able to move into a null screen.
        String key = x + " " + y;
        currentScreen = screens.get(key);
        // make the 4 adjacent screens
        // north
        generateScreen(x, y + 1);

        // east
        generateScreen(x + 1, y);

        // south
        generateScreen(x, y - 1);

        // west
        generateScreen(x - 1, y);
    }

}

