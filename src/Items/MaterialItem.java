package Items;
import Entity.Point;

public class MaterialItem extends Item {
    String materialKey;

    public MaterialItem(String materialKey, Point pos) {
        super(5, pos);
        this.materialKey = materialKey;
        Material mat = Material.materials.get(materialKey);
        this.name = mat.name;
        this.description = "Item of " + mat.name;

    }

    public MaterialItem(String materialKey) {
        this(materialKey, new Point());
    }

    public MaterialItem() {
        this("default");
    }
}
