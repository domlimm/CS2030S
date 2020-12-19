/**
 * The Booking class handles the comparing between which driver
 * holds the lowest fare.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 3
 */
public class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;

    public Booking() {
        this.driver = null;
        this.request = null;
    }

    public Booking(Driver driver, Request r) {
        this.driver = driver;
        this.request = r;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public Request getRequest() {
        return this.request;
    }

    @Override
    public int compareTo(Booking booking) {
        // Compare fares from both taxis
        // Same, take shorter wait time.
        double first_booking = this.getDriver().getLowerFare(this.request);
        double second_booking = booking.getDriver().getLowerFare(booking.request);

        if (first_booking == second_booking) {
            if (this.getDriver().getWaitingTime() < booking.getDriver().getWaitingTime()) {
                return -1;
            }
            else if (this.getDriver().getWaitingTime() > booking.getDriver().getWaitingTime()) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else if (first_booking > second_booking) {
            return 1;
        }
        else if (first_booking < second_booking) {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)"
                , getDriver().getLowerFare(this.request)
                , this.driver, getDriver().selectService(this.request));
    }
}
