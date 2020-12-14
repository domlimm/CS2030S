class Circle {
    private double radius;
    Point pt;

    Circle(Point p, double r) {
        this.pt = p;
        this.radius = r;
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centered at ",
		this.radius) + this.pt.toString();
    }
}
