package cs2030.simulator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The Server class holds the details of a Server.
 */
public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    private final int customerLimit;
    private final Queue<Customer> customerQueue;
    private final boolean isSelfCheckout;

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
        this.customerLimit = 1;
        Queue<Customer> q = new LinkedList<Customer>();
        q.add(new Customer(-9999, 999999.99));
        this.customerQueue = new LinkedList<Customer>(q);
        this.isSelfCheckout = false;
    }

    /**
     * Initialises a new Server used in the Shop class.
     * @param identifier    ID of Server
     */
    public Server(int identifier) {
        this.identifier = identifier;
        this.isAvailable = true;
        this.hasWaitingCustomer = false;
        this.nextAvailableTime = 0.0;
        this.customerLimit = 1;
        this.customerQueue = new LinkedList<Customer>();
        this.isSelfCheckout = false;
    }

    /**
     * Initialises a new Server used for Customer Queue.
     * @param identifier        ID of Server
     * @param customerLimit     Limit the number of customers in each server's queue
     */
    public Server(int identifier, int customerLimit) {
        this.identifier = identifier;
        this.isAvailable = true;
        this.hasWaitingCustomer = false;
        this.nextAvailableTime = 0.0;
        this.customerLimit = customerLimit;
        this.customerQueue = new LinkedList<Customer>();
        this.isSelfCheckout = false;
    }

    /**
     * Initialises a new Server used for Customer Queue.
     * @param identifier        ID of Server
     * @param customerLimit     Limit the number of customers in each server's queue
     * @param isSelfCO          Indicator of self checkout counter
     */
    public Server(int identifier, int customerLimit, boolean isSelfCO) {
        this.identifier = identifier;
        this.isAvailable = true;
        this.hasWaitingCustomer = false;
        this.nextAvailableTime = 0.0;
        this.customerLimit = customerLimit;
        this.customerQueue = new LinkedList<Customer>();
        this.isSelfCheckout = isSelfCO;
    }

    /**
     * Initialises a new Server used for Customer Queue.
     * @param identifier            ID of Server
     * @param isAvailable           Server's availability
     * @param hasWaitingCustomer    Check if Server has a waiting customer
     * @param nextAvailableTime     Time when Server be available
     * @param customerLimit         Limit the number of customers in each server's queue
     * @param pq                    Updated Customer Queue
     * @param isSelfCO              Human server or Selfcheckout counter
     */
    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer,
            double nextAvailableTime, int customerLimit, Queue<Customer> pq,
            boolean isSelfCO) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.customerLimit = customerLimit;
        this.customerQueue = new LinkedList<Customer>(pq);
        this.isSelfCheckout = isSelfCO;
    }

    protected Server queueCustomer(Customer customer) {
        Queue<Customer> tempPQ =
                new LinkedList<Customer>(this.customerQueue);
        tempPQ.add(customer);

        return new Server(
                this.identifier,
                this.isAvailable,
                this.hasWaitingCustomer,
                this.nextAvailableTime,
                this.customerLimit,
                tempPQ,
                this.isSelfCheckout);
    }

    protected Server deQueueCustomer() {
        Queue<Customer> tempPQ =
                new LinkedList<Customer>(this.customerQueue);
        tempPQ.poll();

        return new Server(
                this.identifier,
                this.isAvailable,
                this.hasWaitingCustomer,
                this.nextAvailableTime,
                this.customerLimit,
                tempPQ,
                this.isSelfCheckout);
    }

    protected int customerQueueSize() {
        return this.customerQueue.size();
    }

    protected Server serveRest(double backTime) {
        return new Server(
                this.identifier,
                false,
                this.customerQueue.isEmpty(),
                backTime,
                this.customerLimit,
                this.customerQueue,
                false);
    }

    protected Server serverServe(double nextAvailableTime) {
        return new Server(
                this.identifier,
                false,
                this.customerQueue.isEmpty(),
                nextAvailableTime,
                this.customerLimit,
                this.customerQueue,
                this.isSelfCheckout);
    }

    protected Server serverWait(double nextAvailableTime) {
        return new Server(
                this.identifier,
                false,
                true,
                nextAvailableTime,
                this.customerLimit,
                this.customerQueue,
                this.isSelfCheckout);
    }

    protected Server serverAvailable() {
        return new Server(
                this.identifier,
                true,
                false,
                0.0,
                this.customerLimit,
                this.customerQueue,
                this.isSelfCheckout);
    }

    protected String printServer() {
        return this.isSelfCheckout ?
                "self-check " + this.identifier :
                "server " + this.identifier;
    }

    /**
     * Getters.
     */
    public boolean isSelfCheckout() {
        return this.isSelfCheckout;
    }

    public Queue<Customer> getCustomerQueue() {
        return this.customerQueue;
    }

    public int getCustomerLimit() {
        return this.customerLimit;
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
    
    public boolean isAvailable() {
        return isAvailable;
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
