package divideandconquer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.*;

public class ClosestPairOfPoints {

    /**
     * Takes a list of points and returns the distance between the closest pair. This is done with
     * divide and conquer.
     *
     * @param points - list of points that need to be considered.
     * @return smallest pair-wise distance between points.
     */
    public static double closestPair(List<Point> points) {
        if (points.size() <= 1) return Double.POSITIVE_INFINITY;

        if (points.size() <= 3) return Util.bruteForce(points);

        // Find L, the median of the X axis.
        double L = 0.0;
        for (Point item : points) {
            L += item.x;
        }
        L /= points.size();

        // Split the line by the computed L line.
        List<Point> left = new ArrayList<>();
        List<Point> right = new ArrayList<>();
        for (Point value : points) {
            if (value.x < L) left.add(value);
            else right.add(value);
        }

        // Compute minimum distances in left and right.
        double leftDistance = closestPair(left);
        double rightDistance = closestPair(right);

        // Compute the minimum distance between left and right.
        double delta = Math.min(leftDistance, rightDistance);
        Util.sortByY(points);
        List<Point> withinDelta = new ArrayList<>();
        Point it = null;
        for (Point point : points) {
            it = point;
            if ((it.x > L - delta) && (it.x < L + delta)) withinDelta.add(it);
        }

        double middleDistance = Util.bruteForce(withinDelta);
        delta = Math.min(delta, middleDistance);

        return delta;
    }
}

/** Class representing a 2D point. */
class Point {

    public double x;

    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

/** Useful methods for this assignment. */
class Util {

    // Counts how often the distance function was called.
    // In an exam we may use such counters in places where you cannot edit them ;)
    // For our largest test case, this counter is checked to be less than 3 million. Our solution
    // uses about 1.2 million calls to the distance function.
    static int count_distances = 0;

    /**
     * Takes two points and computes the euclidean distance between the two points.
     *
     * @param point1 - first point.
     * @param point2 - second point.
     * @return euclidean distance between the two points.
     * @see <a
     *     href="https://en.wikipedia.org/wiki/Euclidean_distance">https://en.wikipedia.org/wiki/Euclidean_distance</a>
     */
    public static double distance(Point point1, Point point2) {
        Util.count_distances++;
        return Math.sqrt(Math.pow(point1.x - point2.x, 2.0D) + Math.pow(point1.y - point2.y, 2.0D));
    }

    /**
     * Takes a list of points and sorts it on x (ascending).
     *
     * @param points - points that need to be sorted.
     */
    public static void sortByX(List<Point> points) {
        Collections.sort(points, Comparator.comparingDouble(point -> point.x));
    }

    /**
     * Takes a list of points and sorts it on y (ascending) .
     *
     * @param points - points that need to be sorted.
     */
    public static void sortByY(List<Point> points) {
        Collections.sort(points, Comparator.comparingDouble(point -> point.y));
    }

    /**
     * Takes a list of points and returns the distance between the closest pair. This is done by
     * brute forcing.
     *
     * @param points - list of points that need to be considered.
     * @return smallest pair-wise distance between points.
     */
    public static double bruteForce(List<Point> points) {
        int size = points.size();
        if (size <= 1) return Double.POSITIVE_INFINITY;
        double bestDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < size - 1; i++) {
            Point point1 = points.get(i);
            for (int j = i + 1; j < size; j++) {
                Point point2 = points.get(j);
                double distance = Util.distance(point1, point2);
                if (distance < bestDist) bestDist = distance;
            }
        }
        return bestDist;
    }
}

class ClosestPairOfPointsTest {

    @Test()
    @Timeout(1)
    public void testTwoPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(4, 6));
        Assertions.assertEquals(5, ClosestPairOfPoints.closestPair(points), 5e-6);
    }

    @Test()
    @Timeout(1)
    public void testSmall() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(2, 3));
        points.add(new Point(12, 30));
        points.add(new Point(40, 50));
        points.add(new Point(5, 1));
        points.add(new Point(12, 10));
        points.add(new Point(3, 4));
        Assertions.assertEquals(1.4142135623730951, ClosestPairOfPoints.closestPair(points), 1e-6);
    }
}

