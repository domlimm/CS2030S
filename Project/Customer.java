package cs2030.simulator;

/**
 * The Customer class holds the details of a Customer.
 */
public class Customer {
    private final int customerID;
    private final double arrivalTime;

    /**
     * Initialise a new Customer.
     * @param customerID    ID of Customer
     * @param arrivalTime   Time of Customer's arrival
     */
    public Customer(int customerID, double arrivalTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("%s arrives at %.1f", this.customerID, this.arrivalTime);
    }
}
