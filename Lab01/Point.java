/**
 * The Point class holds x and y coordinates in a Point.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 1
 */
class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point midPoint(Point q) {
        double midX = (this.x + q.x) / 2;
        double midY = (this.y + q.y) / 2;

        return new Point(midX, midY);
    }

    public double angleTo(Point q) {
        return Math.atan2(q.y - this.y, q.x - this.x);
    }

    public Point moveTo(double theta, double d) {
        double newX = this.x + (d * Math.cos(theta));
        double newY = this.y + (d * Math.sin(theta));

        return new Point(newX, newY);
    }

    public double lengthPoints(Point q) {
        return Math.sqrt(Math.pow(q.x - this.x, 2) + Math.pow(q.y - this.y, 2));
    }

    // include a toString method
    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", this.x, this.y);
    }
}
