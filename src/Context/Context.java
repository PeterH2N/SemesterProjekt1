package Context;/* Context.Context class to hold all context relevant to a session.
 */
import World.*;
import Player.*;

public class Context {

  Player player;
  boolean done = false;
  
  public Context() {
  }
  
  public void makeDone () {
    done = true;
  }
  
  public boolean isDone () {
    return done;
  }
}

