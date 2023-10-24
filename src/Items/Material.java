package Items;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import Globals.Globals;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class Material {
    static Map<String, Material> materials;
    String name;
    double density;
    int pricePerKg;
    int decayTime;

    Material(String name, double density, int price, int decayTime) {
        this.name = name;
        this.density = density;
        this.pricePerKg = price;
        this.decayTime = decayTime;
    }

    static public void init() {
        try {
            Gson gson = new Gson();
            Path filePath = Path.of(Globals.JSONpath + "materials.json");
            String json = Files.readString(filePath);
            materials = gson.fromJson(json, new TypeToken<HashMap<String, Material>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
