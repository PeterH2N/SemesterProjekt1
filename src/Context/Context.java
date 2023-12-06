package Context;/* Context.Context class to hold all context relevant to a session.
 */

import World.*;
import Player.*;

public class Context {

    public Player player = new Player();

    public World world = new World();
    boolean done = false;

    public Context() {
    }

    public Space getCurrentSpace() {
        int[] pos = player.getPosition();
        return world.currentScreen.map[pos[1]][pos[0]][pos[2]];
    }

    public void makeDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }
}

