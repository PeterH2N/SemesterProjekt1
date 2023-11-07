package Items;

import Entity.Point;

public class WasteItem extends Item {
    String wasteTypeKey;

    public WasteItem(String wasteItemTypeKey, Point pos) {
        super(5, pos);
        this.wasteTypeKey = wasteItemTypeKey;
        WasteType wt = WasteType.getType(wasteItemTypeKey);
        name = wt.name;
        description = wt.description;
    }

    public WasteItem(String wasteItemTypeKey) {
        this(wasteItemTypeKey, new Point());
    }

    public WasteItem() {
        this("default", new Point());
    }

    String getMaterialName() {
        return WasteType.getType(wasteTypeKey).material().name;
    }
}


