package cs2030.simulator;

import java.util.function.Supplier;

/**
 * The Customer class holds the details of a Customer.
 */
public class Customer {
    private final int customerID;
    private final double arrivalTime;
    private final Supplier<Double> serviceTimeS;
    private final boolean isGreedy;

    /**
     * Initialise a new Customer.
     * @param customerID    ID of Customer
     * @param arrivalTime   Time of Customer's arrival
     */
    public Customer(int customerID, double arrivalTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTimeS = null;
        this.isGreedy = false;
    }

    /**
     * Initialise a new Customer.
     * @param customerID    ID of Customer
     * @param arrivalTime   Time of Customer's arrival
     * @param serviceTimeS  Supplier to supply random service time
     */
    public Customer(int customerID, double arrivalTime, Supplier<Double> serviceTimeS) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTimeS = serviceTimeS;
        this.isGreedy = false;
    }

    /**
     * Initialise a new Greedy Customer.
     * @param customerID    ID of Customer
     * @param arrivalTime   Time of Customer's arrival
     * @param serviceTimeS  Supplier to supply random service time
     * @param greedy        Typical or Greedy Customer
     */
    public Customer(int customerID, double arrivalTime,
            Supplier<Double> serviceTimeS, boolean greedy) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTimeS = serviceTimeS;
        this.isGreedy = greedy;
    }

    protected Customer toGreedy(boolean greedy) {
        return new Customer(customerID, arrivalTime, serviceTimeS, greedy);
    }

    public Supplier<Double> getServiceTimeS() {
        return this.serviceTimeS;
    }

    public boolean isGreedy() {
        return this.isGreedy;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    protected String printCustomer() {
        return this.isGreedy ?
                this.customerID + "(greedy)" :
                String.valueOf(this.customerID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Customer) {
            Customer check = (Customer) o;

            return this.customerID == check.getCustomerID() &&
                    this.arrivalTime == check.getArrivalTime();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s arrives at %.1f", this.customerID, this.arrivalTime);
    }
}
