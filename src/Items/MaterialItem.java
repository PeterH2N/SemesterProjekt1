package Items;
import Entity.Point;

public class MaterialItem extends Item {
    String materialKey;

    MaterialItem(String materialKey, Point pos) {
        super(5, pos);
        this.materialKey = materialKey;
        Material mat = Material.materials.get(materialKey);
        this.name = mat.name;
        this.description = "Item of " + mat.name;

    }

    MaterialItem(String materialKey) {
        this(materialKey, new Point());
    }

    MaterialItem() {
        this("default");
    }
}
