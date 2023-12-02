package Items;
import Entity.Point;
import Globals.Globals;

public class MaterialItem extends Item {
    String materialKey;

    public MaterialItem(String materialKey, Point pos) {
        super(Globals.defaultItemStackSize, pos);
        this.materialKey = materialKey;
        Material mat = Material.materials.get(materialKey);
        this.name = mat.name;
        this.description = mat.description;

    }

    public MaterialItem(String materialKey) {
        this(materialKey, new Point());
    }

    public Material getMaterial() {
        return Material.materials.get(materialKey);
    }

    public MaterialItem() {
        this("default");
    }
}
