package Items;

import Entity.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Globals.Globals;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.nio.file.Files;
import java.nio.file.Path;

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

    String getMaterialName() {
        return WasteType.getType(wasteItemTypeKey).material().name;
    }
}


