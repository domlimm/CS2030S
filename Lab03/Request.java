/**
 * The Request class holds properties of a request submitted by a passenger.
 * 
 * @author Dominic Lim
 * @version: CS2030S AY20/21 Semester 1, Lab 3
 */
public class Request {
    private final int distance;
    private final int noOfPassengers;
    private final int time;

    public Request(int distance, int noOfPassengers, int time) {
        this.distance = distance;
        this.noOfPassengers = noOfPassengers;
        this.time = time;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getNoOfPassengers() {
        return this.noOfPassengers;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", this.distance, this.noOfPassengers, this.time);
    }
}
