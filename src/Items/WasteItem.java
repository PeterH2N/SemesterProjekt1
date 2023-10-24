package Items;

import Entity.Point;
import java.util.HashMap;
import java.util.Map;

import Globals.Globals;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.nio.file.Files;
import java.nio.file.Path;

public class WasteItem extends Item
{
    String wasteItemTypeKey;

    public WasteItem(String wasteItemTypeKey, Point pos)
    {
        super(64, pos);
        this.wasteItemTypeKey = wasteItemTypeKey;
        WasteType wt = WasteType.getType(wasteItemTypeKey);
        name = wt.name;
        description = wt.description;
    }

    public WasteItem(String wasteItemTypeKey)
    {
        this(wasteItemTypeKey, new Point());
    }

    public WasteItem()
    {
        this("default", new Point());
    }

    String getMaterialName()
    {
        return WasteType.getType(wasteItemTypeKey).material().name;
    }
}

class WasteType
{
    String name;
    String description;
    String materialKey;
    double volume;

    WasteType(String name, String description, String materialKey, double volume)
    {
        this.name = name;
        this.description = description;
        this.materialKey = materialKey;
        this.volume = volume;
    }

    Material material()
    {
        return Material.materials.get(materialKey);
    }

    public double getWeight()
    {
        return volume * material().density;
    }

    public int getPrice()
    {
        return (int) ((double) material().pricePerKg * getWeight());
    }

    static Map<String, WasteType> wasteItemTypes;

    static WasteType getType(String key)
    {
        return wasteItemTypes.get(key);
    }

    static void init()
    {
        try
        {
            Gson gson = new Gson();
            Path filePath = Path.of(Globals.JSONpath + "wasteTypes.json");
            String json = Files.readString(filePath);
            wasteItemTypes = gson.fromJson(json, new TypeToken<HashMap<String, WasteType>>(){}.getType());
        }
        catch (Exception e) {e.printStackTrace();}
    }
}