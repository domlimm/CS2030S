package cs2030.simulator;

/**
 * The Server class holds the details of a Server.
 */
public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    /**
     * Initialises a new Server.
     * @param identifier            ID of Server
     * @param isAvailable           Availability of Server
     * @param hasWaitingCustomer    Server has a Customer waiting
     * @param nextAvailableTime     Time of when the server will be available
     */
    public Server(int identifier, boolean isAvailable,
            boolean hasWaitingCustomer, double nextAvailableTime) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

    /**
     * Initialises a new Server with pre-defined data. 
     * @param nextAvailableTime The time when the server will be available
     * @return Server Returns a new Server with pre-defined data.
     */
    protected Server serverServe(double nextAvailableTime) {
        return new Server(this.identifier, false, false, nextAvailableTime);
    }

    /**
     * Initialises a new Server with pre-defined data. 
     * @param nextAvailableTime The time when the server will be available
     * @return Server Returns a new Server with pre-defined data.
     */
    protected Server serverWait(double nextAvailableTime) {
        return new Server(this.identifier, false, true, nextAvailableTime);
    }

    /**
     * Initialises a new Server with pre-defined data. 
     * @return Server Returns a new Server with pre-defined data.
     */
    protected Server serverAvailable() {
        return new Server(this.identifier, true, false, 0.0);
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean getAvailability() {
        return this.isAvailable;
    }

    public boolean getHasWaitingCustomer() {
        return this.hasWaitingCustomer;
    }

    public double getAvailableTime() {
        return this.nextAvailableTime;
    }

    @Override
    public String toString() {
        String message = "";

        if (this.isAvailable) {
            message = String.format("%d is available", this.identifier);
        } else {
            if (this.hasWaitingCustomer) {
                message = String.format("%d is busy; waiting customer to be served at %.3f",
                        this.identifier, this.nextAvailableTime);
            } else {
                message = String.format("%d is busy; available at %.3f",
                        this.identifier, this.nextAvailableTime);
            }
        }

        return message;
    }
}
