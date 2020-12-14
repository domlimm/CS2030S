Circle createUnitCircle(Point p, Point q) {
    // Midpoint of Line PQ
    Point midptPQ = p.midPoint(q);

    double angle = p.angleTo(q) + Math.PI / 2;

    // Length of P/Q to Midpoint m
    double lengthPQ = p.lengthPoints(q);

    // Length of d (midptPQ to center of circle)
    double d = Math.sqrt(Math.pow(1, 2) - Math.pow(lengthPQ / 2, 2));

    // Point c
    Point c = midptPQ.moveTo(angle, d);

    return new Circle(c, 1.0);
}

int findMaxDiscCoverage(Point[] points) {
    int maxDiscCoverage = 0;

    for (int i = 0; i < points.length - 1; ++i) {
        for (int j = i + 1; j < points.length; ++j) {
           Circle tempCircle = createUnitCircle(points[i], points[j]);
           int tempPoints = 0;

           for (int k = 0; k < points.length; ++k) {
                double checkLength = Math.abs(points[k].lengthPoints(tempCircle.pt));

                if (checkLength <= 1) {
                    ++tempPoints;
                }
           }

           if (tempPoints > maxDiscCoverage) {
                maxDiscCoverage = tempPoints;
           }
        }
    }

    return maxDiscCoverage;
}
