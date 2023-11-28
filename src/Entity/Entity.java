package Entity;

public class Entity {
    // we don't expect the game to contain more entities than int.max... then we'd have more problems
    static protected int uniqueID = 0;
    protected Point position;
    private double rotation;
    protected int id = uniqueID++;

    protected Entity(Point p, double rot) {
        position = p;
        setRotation(rot);
    }

    protected Entity(Point p) {
        this(p, 0);
    }

    protected Entity() {
        this(new Point());
    }

    final public Point getPosition() {
        return position.copy();
    }

    final protected Point setPosition(Point p) {
        Point old = position;
        position = p;
        return old;
    }

    final protected Point setPosition(double x, double y) {
        Point old = position;
        position.x = x;
        position.y = y;
        return old;
    }

    final public int getID() {
        return id;
    }

    public double getRotation()
    {
        return rotation;
    }

    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }
}
