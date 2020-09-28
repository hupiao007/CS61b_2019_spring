package bearmaps;

import java.util.List;

public class KDTree implements PointSet{
    //Node class for BST, as implementation of kdtree
    //horizontal true means x direcion, left and right space of the point
    private class Node {
        private Point p;
        private Node left, right;
        private boolean horizontal;

        private Node(Point x, boolean xOrY) {
            p = x;
            left = null;
            right = null;
            horizontal = xOrY;
        }

        private boolean horGetter() {return horizontal;}

        private Point pGetter() {return p;}
    }

    /** tie breaker, >=
     * for y axis, down -- leftChild, up -- rightChild
     */
    private Node root;
    public KDTree(List<Point> points) {
        root = new Node(points.get(0), true);
        for (int i = 1; i < points.size(); i++) {
            root = add(root, points.get(i), true);
        }
    }

    private Node add(Node root, Point p, boolean xY) {
        if (root == null) return new Node(p, xY); //??????
        if (root.horGetter()) {
            if (p.getX() >= root.pGetter().getX()) {
                root.right = add(root.right, p, !xY);
            } else {root.left = add(root.left, p, !xY);}
        } else {
            if (p.getY() >= root.pGetter().getY()) {
                root.right = add(root.right, p, !xY);
            } else {root.left = add(root.left, p, !xY);}
        }
        return root;
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearestHepler(root, goal, root).p;
    }
    private Node nearestHepler(Node n, Point goal, Node best) {
        double minDis = Point.distance(goal, best.p);
        if (n == null) return best;
        if (Point.distance(goal, n.p) < minDis) best = n;
        if (n.horizontal) {
            Node goodSide = goal.getX() < n.p.getX() ? n.left : n.right;
            Node badSide = goodSide == n.left? n.right:n.left;
            best = nearestHepler(goodSide, goal, best);
            if (Math.pow(goal.getX() - n.p.getX(), 2) < minDis) {
                best = nearestHepler(badSide, goal, best);
            }
        } else {
            Node goodSide = goal.getY() < n.p.getY() ? n.left : n.right;
            Node badSide = goodSide == n.left? n.right:n.left;
            best = nearestHepler(goodSide, goal, best);
            if (Math.pow(goal.getY() - n.p.getY(), 2) < minDis) {
                best = nearestHepler(badSide, goal, best);
            }
        }
        return best;
    }
}
