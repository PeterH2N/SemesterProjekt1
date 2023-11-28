package Items;

import Globals.Globals;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WasteType {
    public static List<String> wasteTypeKeys;
    public static Map<String, WasteType> wasteTypes;
    String name;
    String description;
    String materialKey;
    double volume;

    private int index;



    static
    {
        init();
    }

    WasteType(String name, String description, String materialKey, double volume, int index) {
        this.name = name;
        this.description = description;
        this.materialKey = materialKey;
        this.volume = volume;
        this.index = index;
    }

    static WasteType getType(String key) {
        return wasteTypes.get(key);
    }

    static void init() {
        try {
            Gson gson = new Gson();
            Path filePath = Path.of(Globals.JSONPath + "wasteTypes.json");
            String json = Files.readString(filePath);
            wasteTypes = gson.fromJson(json, new TypeToken<HashMap<String, WasteType>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        wasteTypeKeys = new ArrayList<String>(wasteTypes.keySet());
    }

    Material material() {
        return Material.materials.get(materialKey);
    }

    public double getWeight() {
        return volume * material().density;
    }

    public int getPrice() {
        return (int) ((double) material().pricePerKg * getWeight());
    }

   public String getInfo(){
        return "Name: " + material().name + "\n" +
               "Density: " + material().density + "\n" +
               "Price Per Kg: " + material().pricePerKg + "\n" +
               "Decay Time: " + material().decayTime;
   }

    @Override
    public String toString(){
        return "Trash: " + name + "\n Type: " + name + "\n Number: " + getIndex() + "\nDescription: " + description;
    }

    public int getIndex() {
        return index;
    }
}
