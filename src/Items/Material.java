package Items;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import Globals.Globals;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Material {
    static Map<String, Material> materials;
    String name;
    String longName;
    String description;
    double density;
    public int pricePerItem;
    int decayTime;

    Material(String name, String longName, String description, double density, int price, int decayTime) {
        this.name = name;
        this.longName = longName;
        this.description = description;
        this.density = density;
        this.pricePerItem = price;
        this.decayTime = decayTime;
    }

    static public void init() {
        try {
            Gson gson = new Gson();
            Path filePath = Path.of(Globals.JSONPath + "materials.json");
            String json = Files.readString(filePath);
            materials = gson.fromJson(json, new TypeToken<HashMap<String, Material>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
