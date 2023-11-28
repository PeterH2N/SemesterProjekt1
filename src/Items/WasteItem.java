package Items;

import Entity.Point;
import Globals.Globals;

public class WasteItem extends Item {
    String wasteTypeKey;

    public WasteItem(String wasteItemTypeKey, Point pos) {
        super(Globals.defaultItemStackSize, pos);
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

    public String getMaterialKey() {
        return WasteType.getType(wasteTypeKey).materialKey;
    }

    String getWasteTypeKey() {
        return wasteTypeKey;
    }

    public String getWasteItemInfo(){
        return  WasteType.getType(wasteTypeKey).getInfo();
    }
}


