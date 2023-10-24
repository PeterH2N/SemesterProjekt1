package Items;

import Entity.*;

public class Item extends Entity {
    // initializing all the subclasses (should this be done here?
    static {
        Material.init();
        WasteType.init();
    }

    protected String name;
    protected String description;
    protected int stackSize;

    protected Item(int size, Point p) {
        super(p);
        this.stackSize = size;

    }

    protected Item() {
        this(64, new Point());
    }

    final public int getStackSize() {
        return stackSize;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}


