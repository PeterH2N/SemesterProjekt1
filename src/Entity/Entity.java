package Entity;

public class Entity {
    // we don't expect the game to contain more entities than int.max... then we'd have a problem
    static protected int uniqueID = 0;
    protected Point position;
    protected int id = uniqueID++;

    protected Entity(Point p) {
        position = p;
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
}
