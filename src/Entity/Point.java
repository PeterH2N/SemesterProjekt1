package Entity;

public class Point
{
    public double x, y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Point() {}

    public Point copy()
    {
        return new Point(this.x, this.y);
    }

    static public double distance(Point a, Point b)
    {
        double x = b.x - a.x;
        double y = b.y - a.y;

        return Math.sqrt((x * x) + (y * y));
    }
}
