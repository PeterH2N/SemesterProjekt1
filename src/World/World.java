package World;/* World.World class for modeling the entire in-game world
 */

public class World {
  Space entry;
  
  public World () {
    Space entry    = new Space("Entry");
    Space corridor = new Space("Corridor");
    Space cave     = new Space("Cave");
    Space pit      = new Space("Darkest Pit");
    Space outside  = new Space("Outside");
    
    entry.addEdge("door", corridor);
    corridor.addEdge("door", cave);
    cave.addEdge("north", pit);
    cave.addEdge("south", outside);
    pit.addEdge("door", cave);
    outside.addEdge("door", cave);
    
    this.entry = entry;
  }
  
  public Space getEntry() {
    return entry;
  }
}

