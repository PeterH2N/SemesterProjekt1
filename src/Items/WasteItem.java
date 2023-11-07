package Items;

import Entity.Point;

public class WasteItem extends Item {
    String wasteItemTypeKey;

    public WasteItem(String wasteItemTypeKey, Point pos) {
        super(5, pos);
        this.wasteItemTypeKey = wasteItemTypeKey;
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

    String getMaterialKey() {
        return WasteType.getType(wasteItemTypeKey).materialKey;
    }
}


