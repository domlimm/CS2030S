Booking findBestBooking(Request request, Driver[] drivers) {
    int totalDrivers = drivers.length;
    Booking bestBooking = new Booking(drivers[0], request);

    for (int i = 1; i < totalDrivers; ++i) {
        Booking current = new Booking(drivers[i], request);

        if (bestBooking.compareTo(current) > 0) {
            bestBooking = current;
        }
    }

    return bestBooking;
}