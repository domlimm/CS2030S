package cs2030.simulator;

import java.util.List;

/**
 * This LeaveEvent class inherits the properties of its parent Event class.
 * It is used when the queue is full when the customer arrives.
 */
public class LeaveEvent extends Event {
    /**
     * Initialises a new LeaveEvent.
     * @param customer  Customer with his/her details
     * @param servers   A list of servers and their details
     */
    public LeaveEvent(Customer customer, List<Server> servers) {
        super(customer, servers, customer.getArrivalTime(), 'L');
    }

    /**
     * This method carries over the updated servers list to the rest of the 
     * customers in the queue.
     * @return Event Returns LeaveEvent itself.
     */
    public Event execute() {
        return new LeaveEvent(super.getCustomer(), super.getServers());
    }

    /**
     * This method updates the statistics of the simulation.
     * @param s Takes in a Statistics object
     * @return Statistics Returns a statistics object with its attributes.
     */
    public Statistics updateStatistics(Statistics s) {
        return new Statistics(s.getNumCustomersServed(),
                s.getNumCustomersLeft() + 1, s.getTotalWaitTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves",
                super.getCustomer().getArrivalTime(),
                super.getCustomer().getCustomerID());
    }
}
