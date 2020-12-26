public Logger<Integer> add(Logger<Integer> a, int b) {
    return a.map(x -> x + b);
}

public Logger<Integer> sum(int n) {
    if (n == 0) {
        return Logger.make(n);
    } else {
        return add(sum(n - 1), n);
    }
}

public Logger<Integer> f(int n) {
    Logger<Integer> logger = Logger.make(n);

    if (logger.test(x -> x == 1)) {
        return logger.flatMap(x -> Logger.make(x));
    } else if (logger.test(x -> x % 2 == 0)) {
        return logger.map(x -> x / 2).flatMap(x -> f(x));
    } else {
        return logger.map(x -> 3 * x).map(y -> y + 1).flatMap(z -> f(z));
    }
}
