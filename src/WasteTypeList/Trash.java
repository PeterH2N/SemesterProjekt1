package WasteTypeList;
import java.util.*;
import Items.*;
//styre "pokedexet"
public class Trash {
    private Map<Integer, String> trashMap;

    public Trash() {
        trashMap = new HashMap<>();
    }

    public void addTrash(String trash) {
        trashMap.put(WasteType.wasteTypes.get(trash).getIndex(), trash);
    }

    public String getTrash(int number) {
        return trashMap.getOrDefault(number, null);
    }
    public void listAllTrash(){
        System.out.println("Trash in the trashIndex");
        for (String trash : trashMap.values()){
            System.out.println("-------------------");
            System.out.println(WasteType.wasteTypes.get(trash));
            System.out.println("-------------------");
        }
    }
}
