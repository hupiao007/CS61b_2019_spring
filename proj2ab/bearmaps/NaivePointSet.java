package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> nn;
    public NaivePointSet(List<Point> points) {
        nn = points;
    }
    @Override
    public Point nearest(double x, double y) {
        Point nearest = new Point(0, 0);
        Point givenP = new Point(x, y);
        double line = Point.distance(nn.get(0), givenP);
        for (Point p : nn) {
            if (Point.distance(p, givenP) < line) {
                nearest = p;
                line = Point.distance(p, givenP);
            }
        }
        return nearest;
    }
}
