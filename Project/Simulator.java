package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This Simulator class is the handler of the discrete event simulator with
 * the process of assigning/removing servers to customers waiting
 * in a priority queue.
 */
public class Simulator {
    private final List<Server> servers;
    private final List<Customer> customers;
    private final PriorityQueue<Event> pq;

    /**
     * Initialise a new Simulator.
     * @param servers   List of servers and their details
     * @param customers List of customers and their details
     */
    public Simulator(List<Server> servers, List<Customer> customers) {
        this.servers = servers;
        this.customers = customers;
        this.pq = new PriorityQueue<Event>(new EventCompare());
    }

    /**
     * This method automates the simulation.
     */
    public Event simulateNextEvent() {
        Event e = this.pq.poll();
        addNewCustomer(e.getEventTime(), e.getCustomer(), e.execute().getServers());
        
        if (e.getEventType() != 'L' && e.getEventType() != 'D') {
            this.pq.add(e.execute());
        }

        return e;
    }

    /**
     * This method initialises the queue with the first customer.
     */
    public void startQueue() {
        this.pq.add(new ArriveEvent(this.customers.get(0), this.servers));
    }

    private void addNewCustomer(double eventTime, Customer customer, List<Server> latestServers) {
        List<Customer> tempC = new ArrayList<Customer>(this.customers);

        for (Customer c: this.customers) {
            boolean isInQueue = isCustomerInQueue(c);
            boolean isNextCustomer = customer.getCustomerID() + 1 == c.getCustomerID();
            boolean isArrivalLater = c.getArrivalTime() >= eventTime;

            if (!isInQueue && isNextCustomer && isArrivalLater) {
                this.pq.add(new ArriveEvent(c, latestServers));
                return;
            }
        }
    }

    private boolean isCustomerInQueue(Customer customer) {
        PriorityQueue<Event> tempPQ = new PriorityQueue<Event>(this.pq);
        List<Customer> customersInQ = new ArrayList<Customer>();

        while (tempPQ.peek() != null) {
            Event e = tempPQ.poll();
            Customer c = e.getCustomer();
            
            if (c.getCustomerID() == customer.getCustomerID()) {
                return true;
            }
        }

        return false;
    }

    /**
     * This methods checks if the queue is empty.
     * @return boolean Returns if the queue is empty
     */
    public boolean hasNextEvent() {
        if (this.pq.size() > 0) {
            return true;
        }

        return false;
    }
}
